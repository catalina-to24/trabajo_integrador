window.addEventListener('load', function () {

    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //let pacienteId = document.querySelector('#paciente_id').value;

        const formData = {
            id: document.querySelector('#turno_id').value,
            fechaHora: document.querySelector('#fechaHora').value,
            pacienteId: document.querySelector('#paciente').selectedOptions[0].value,
            odontologoId: document.querySelector('#odontologo').selectedOptions[0].value,
        };

        console.log(formData);

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => {
            if (response.ok){
             let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                 '<strong></strong> Turno actualizado </div>'

                             document.querySelector('#response').innerHTML = successAlert;
                             document.querySelector('#response').style.display = "block";
                }
            })

    })
 })

    function findBy(id) {
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#fechaHora').value = turno.fechaHora;

              loadPacientes(turno.paciente);
              loadOdontologos(turno.odontologo);

              document.querySelector('#div_turno_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }

      function loadPacientes(pacienteSelected){
       const url = '/pacientes'
         const settings = {
             method: 'GET'
         }
         fetch(url,settings)
         .then(response => response.json())
         .then(data => {
           let formPaciente = document.querySelector('#paciente');
           for(paciente of data){
              let option = document.createElement('option');
              option.text = paciente.dni + " - "+paciente.nombre+" "+paciente.apellido;
              option.value = paciente.id;
              if (pacienteSelected.id == paciente.id){
                option.selected = true;
              }
              formPaciente.add(option,null);
           }
         });
      }

      function loadOdontologos(odontologoSelected){
             const url = '/odontologos'
               const settings = {
                   method: 'GET'
               }
               fetch(url,settings)
               .then(response => response.json())
               .then(data => {
                 let formOdontologo = document.querySelector('#odontologo');
                 for(odontologo of data){
                    let option = document.createElement('option');
                    option.text = odontologo.nombre + " "+odontologo.apellido+" - "+odontologo.matricula;
                    option.value = odontologo.id;
                    if (odontologoSelected.id == odontologo.id){
                      option.selected = true;
                    }
                    formOdontologo.add(option,null);
                 }
               });
            }