function deleteBy(id)
{
          //con fetch invocamos a la API de odontologos con el método DELETE
          //pasandole el id en la URL
          const url = '/odontologos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => {
             if(response.ok){
             //borrar la fila de la odontologo eliminado
               let row_id = "#tr_" + id;
               document.querySelector(row_id).remove();
             }
          })
          .catch((error) => {
              console.log(error);
              if (error.status == 404) {
                //Odontólgo no existe
                alert("Odontólgo no existe");
              } else if (error.status == 500) {
                //Error del servidor
                alert("Error del servidor");
              } else {
                alert("Oops! Algo salió mal");
              }
            });
}