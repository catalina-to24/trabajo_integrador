package com.dh.TPIdiego_cata.dao.implementation;

import com.dh.TPIdiego_cata.dao.BD;
import com.dh.TPIdiego_cata.dao.IDao;
import com.dh.TPIdiego_cata.model.Domicilio;
import com.dh.TPIdiego_cata.model.Paciente;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);

    private static final String INSERT_PACIENTE = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, DOMICILIO_ID) VALUES (?,?,?,?,?)";
    private static final String UPDATE_PACIENTE = "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA_INGRESO = ?, DOMICILIO_ID = ?  WHERE ID = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM PACIENTES WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM PACIENTES WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM PACIENTES";

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Persistiendo un paciente");
        Connection connection = null;

        try {
            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.guardar(paciente.getDomicilio());

            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getDni());
            psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psInsert.setInt(5, paciente.getDomicilio().getId());

            psInsert.execute();

            ResultSet resultSet = psInsert.getGeneratedKeys();
            while (resultSet.next()) {
                paciente.setId(resultSet.getInt(1));
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente buscarPorId(Integer id) {
        Connection conexion = null;
        Paciente paciente = null;
        try {

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();

            conexion = BD.getConnection();
            PreparedStatement psSearchByID =  conexion.prepareStatement(SEARCH_BY_ID);
            psSearchByID.setInt(1, id);
            ResultSet rs  = psSearchByID.executeQuery();

            while (rs.next()){
                paciente = new Paciente();
                paciente.setId(rs.getInt("ID"));
                paciente.setNombre(rs.getString("NOMBRE"));
                paciente.setApellido(rs.getString("APELLIDO"));
                paciente.setDni(rs.getString("DNI"));
                paciente.setFechaIngreso(rs.getDate("FECHA_INGRESO").toLocalDate());
                paciente.setDomicilio(domicilioDaoH2.buscarPorId(rs.getInt("DOMICILIO_ID")));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return paciente;
    }

    @Override
    public void eliminar(Integer id) {
        Connection connection = null;
        try {

            //TODO BUSCAR PACIENTE PARA OBTENER EL DOMICILIO Y LUEGO PODER BORRARLO?
            Paciente pacienteBuscado = buscarPorId(id);
            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.eliminar(pacienteBuscado.getDomicilio().getId());

            connection = BD.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void actualizar(Paciente paciente) {
        LOGGER.info("Actualizando un paciente");
        Connection connection = null;

        try {
            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(UPDATE_PACIENTE);
            psUpdate.setString(1, paciente.getNombre());
            psUpdate.setString(2, paciente.getApellido());
            psUpdate.setString(3, paciente.getDni());
            psUpdate.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            psUpdate.setInt(5, paciente.getDomicilio().getId());
            psUpdate.setInt(6, paciente.getId());

            psUpdate.execute();

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.actualizar(paciente.getDomicilio());

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Paciente> listarTodos() {
        LOGGER.info("Consultando todos los pacientes");
        Connection connection = null;
        List<Paciente> pacienteList = new ArrayList<>();
        Paciente paciente = null;

        DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();

        try {
            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SELECT_ALL);
            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                Domicilio domicilio = domicilioDaoH2.buscarPorId(rs.getInt("DOMICILIO_ID"));
                paciente = new Paciente(rs.getInt("ID"),rs.getString("NOMBRE"),
                        rs.getString("APELLIDO"), rs.getString("DNI"), rs.getDate("FECHA_INGRESO").toLocalDate(), domicilio);

                pacienteList.add(paciente);
            }

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pacienteList;
    }
}
