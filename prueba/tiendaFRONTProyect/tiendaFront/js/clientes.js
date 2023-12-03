function buscarTodosLosClientes(){
  var tabla=document.querySelector("#tabla");
  $.ajax({
    url: "http://localhost:8080/listar",
    type: "GET",
    dataType: "json",
    success: function(respuesta){
        $("#tabla tbody").remove();
        for(i=0;i<respuesta.length;i++){
            tabla.innerHTML += '<tr><td>' + respuesta[i].cc_clientes +
            '</td><td>' + respuesta[i].nomCliente +
            '</td><td>' + respuesta[i].telefono +
            '</td><td>' + respuesta[i].correo +
            '</td><td>' + "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarCliente(\""+respuesta[i].cc_clientes+"\")'> Eliminar</a> <a href='#'  onclick='cargar(\""+respuesta[i].cc_clientes+"\")'> Editar</a>" +
            '</td></tr>';
        }      
    }
})
}

function buscarClientePorId(){
  var tabla=document.querySelector("#tabla");
  var cedula=$("#porId").val();
  $.ajax({
    url: "http://localhost:8080/unico/"+cedula,
    type: "GET",
    dataType: "json",
    success: function(respuesta){
            $("#tabla tbody").remove();
            tabla.innerHTML += '<tr><td>' + respuesta.cc_clientes +
            '</td><td>' + respuesta.nomCliente +
            '</td><td>' + respuesta.telefono +
            '</td><td>' + respuesta.correo +
            '</td><td>' + "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarCliente(\""+respuesta.cc_clientes+"\")'> Eliminar</a> <a href='#'  onclick='cargar(\""+respuesta.cc_clientes+"\")'> Editar</a>" +
            '</td></tr>';
            
    },
    error: function (xhr) {
      if (xhr.status === 404) {
        alert("La cedula no existe...");
      }
    },
})
}

function buscarClientesCompra(){
  var tabla=document.querySelector("#tabla");
  $.ajax({
    url: "http://localhost:8080/clientesCompra",
    type: "GET",
    dataType: "json",
    success: function(respuesta){
            $("#tabla tbody").remove();
            for(i=0;i<respuesta.length;i++){
              tabla.innerHTML += '<tr><td>' + respuesta[i][0] +
              '</td><td>' + respuesta[i][1] +
              '</td><td>' + respuesta[i][3] +
              '</td><td>' + respuesta[i][2] +
              '</td><td>' + "Total compra: "+respuesta[i][4] +
              '</td></tr>';
            }    
            
    },
})
}

function insertarCliente(){
  var cedula=$("#cedula").val();
  var nombre=$("#nombre").val();
  var telefono=$("#telefono").val();
  var correo=$("#correo").val();
  data={
    cc_clientes: cedula,
    nomCliente: nombre,
    telefono: telefono,
    correo: correo,
  }
  $.ajax({
      url:"http://localhost:8080/insertar",
      type:"POST",
      data: JSON.stringify(data),
      contentType:"application/json",
      success: function(){
        $("#cedula").val('');
        $("#nombre").val('');
        $("#telefono").val('');
        $("#correo").val('');
        buscarTodosLosClientes()
      },
      error: function(xhr) {
          if(xhr.status===409){
            alert("La cedula existe...")
          }
      }
  })
}
 
function cargar(cedula){
  $.ajax({
      url: "http://localhost:8080/unico/"+cedula,
      type: "GET",
      dataType: "json",
      success: function(respuesta){
          $("#cedula").val(respuesta.cc_clientes)
          $("#cedula").prop("disabled", true)
          $("#agregar").prop("disabled", true)
          $("#actualizar").prop("disabled", false)
          $("#nombre").val(respuesta.nomCliente);
          $("#telefono").val(respuesta.telefono);
          $("#correo").val(respuesta.correo);
      }
  })
}

function actualizarCliente(){
  var cedula=$("#cedula").val();
  var nombre=$("#nombre").val();
  var telefono=$("#telefono").val();
  var correo=$("#correo").val();
  data={
    cc_clientes: cedula,
    nomCliente: nombre,
    telefono: telefono,
    correo: correo,
  }
  $.ajax({
      url:"http://localhost:8080/actualizarSave",
      type:"PUT",
      data: JSON.stringify(data),
      contentType:"application/json",
      success: function(){
          $("#cedula").prop("disabled", false)
          $("#agregar").prop("disabled", false)
          $("#actualizar").prop("disabled", true)
          $("#cedula").val('');
          $("#nombre").val('');
          $("#telefono").val('');
          $("#correo").val('');
          buscarTodosLosClientes()
      },
      error: function(xhr) {
      }  
  })
}
function eliminarCliente(cedula){
  $("#eliminar").off("click").on("click", function(){
      $.ajax({
          url: "http://localhost:8080/eliminar/"+cedula,
          type: "DELETE",
          success: function(){
            $("#exampleModal").modal("hide");  
            buscarTodosLosClientes()
          }
      })
    })
}

