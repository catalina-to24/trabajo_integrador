window.addEventListener('load', function () {
    (function(){

      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {

         for(turno of data){

            var table = document.getElementById("turnoTable");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;

            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                              ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                              ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                              turno.id +
                              '</button>';
            //let fechaHora = new Date(turno.fechaHora);
            let fechaMoment = moment(turno.fechaHora);

            turnoRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_id\">' + turno.id + '</td>' +
                    '<td class=\"td_fechahora\">' + fechaMoment.format('DD/MM/YYYY - HH:mm') + '</td>' +
                    '<td class=\"td_dni\">' + turno.paciente.dni + '</td>' +
                    '<td class=\"td_paciente\" data-paciente="'+turno.paciente.id+'">' + turno.paciente.nombre + ' '+turno.paciente.apellido+'</td>' +
                    '<td class=\"td_odontologo\" data-odontologo="'+turno.odontologo.id+'">' + turno.odontologo.nombre + ' '+turno.odontologo.apellido+'</td>' +
                    '<td>' + deleteButton + '</td>';
        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnoLista.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })