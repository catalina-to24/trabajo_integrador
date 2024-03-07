package com.dh.TPIdiego_cata.dao.implementation;

import com.dh.TPIdiego_cata.dao.BD;
import com.dh.TPIdiego_cata.dao.IDao;
import com.dh.TPIdiego_cata.model.Domicilio;
import com.dh.TPIdiego_cata.model.Paciente;
import org.apache.log4j.Logger;
import java.time.LocalDate;

import java.sql.*;
import java.util.List;

public class PacienteDaoH2 implements IDao<Paciente> {

    private static final Logger LOGGER = Logger.getLogger(PacienteDaoH2.class);

    private static final String INSERT_PACIENTE = "INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA_INGRESO, ID_DOMICILIO) VALUES (?,?,?,?,?)";
    private static final String UPDATE_PACIENTE = "UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA_INGRESO = ?, ID_DOMICILIO = ?  WHERE ID = ?";
    private static final String SEARCH_BY_ID = "SELECT * FROM PACIENTES WHERE ID = ?";
    private static final String DELETE_BY_ID = "DELETE FROM PACIENTES WHERE ID = ?";
    private static final String SELECT_ALL = "SELECT * FROM PACIENTES";

    @Override
    public Paciente guardar(Paciente paciente) {
        LOGGER.info("Persistiendo un paciente");
        Connection connection = null;

        try {
            //DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            //domicilioDaoH2.guardar(paciente.getDomicilio());
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(INSERT_PACIENTE, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, paciente.getNombre());
            psInsert.setString(2, paciente.getApellido());
            psInsert.setString(3, paciente.getDni());
            psInsert.setDate(4, new Date(paciente.getFechaIngreso().getYear(),paciente.getFechaIngreso().getMonthValue(),paciente.getFechaIngreso().getDayOfMonth()));
           // psInsert.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
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
                Date fecha = rs.getDate("FECHA_INGRESO");
                paciente.setFechaIngreso(LocalDate.of(fecha.getYear(),fecha.getMonth(),fecha.getDate()));
                Domicilio domicilio = new Domicilio();
                domicilio.setId(rs.getInt("ID_DOMICILIO"));
                paciente.setDomicilio(domicilio);
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

    }

    @Override
    public void actualizar(Paciente paciente) {

    }

    @Override
    public List<Paciente> listarTodos() {
        return null;
    }
}
