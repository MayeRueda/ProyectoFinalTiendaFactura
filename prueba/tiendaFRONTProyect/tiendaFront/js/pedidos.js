function buscarTodosLosPedidos() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
      url: "http://localhost:8080/listarpd",
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#tabla tbody").remove();
        for (i = 0; i < respuesta.length; i++) {
          tabla.innerHTML +=
            '<tr><td>' +
            respuesta[i].cod_Pedido +
            '</td><td>' +
            respuesta[i].fechapedido +
            '</td><td>' +
            respuesta[i].horapedido +
            '</td><td>' +
            respuesta[i].can_Total +
            '</td><td>' +
            respuesta[i].can_res +
            '</td><td>' +
            respuesta[i].costoNeto +
            '</td><td>' +
            respuesta[i].distribuidoresA.nitDistribuidor +
            '</td><td>' +
            "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarPedido(\"" +
            respuesta[i].cod_Pedido +
            "\")'> Eliminar</a> <a href='#'  onclick='cargarPedido(\"" +
            respuesta[i].cod_Pedido +
            "\")'> Editar</a>" +
            '</td></tr>';
        }
      },
    });
  }
  
  function buscarPedidoPorId() {
    var tabla = document.querySelector("#tabla");
    var codigo = $("#porId").val();
    $.ajax({
      url: "http://localhost:8080/unicopd/" + codigo,
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#tabla tbody").remove();
        tabla.innerHTML +=
          '<tr><td>' +
          respuesta.cod_Pedido +
          '</td><td>' +
          respuesta.fechapedido +
          '</td><td>' +
          respuesta.horapedido +
          '</td><td>' +
          respuesta.can_Total +
          '</td><td>' +
          respuesta.costoNeto +
          '</td><td>' +
          respuesta.can_res +
          '</td><td>' +
          respuesta.distribuidoresA.nitDistribuidor +
          '</td><td>' +
          "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarPedido(\"" +
          respuesta.cod_Pedido +
          "\")'> Eliminar</a> <a href='#'  onclick='cargarPedido(\"" +
          respuesta.cod_Pedido +
          "\")'> Editar</a>" +
          '</td></tr>';
      },
      error: function (xhr) {
        if (xhr.status === 404) {
          alert("El codigo de pedido no existe...");
        }
    },
    });
  }
  
  function insertarPedido() {
    var codigo = $("#codigo").val();
    var cantidad = $("#cantidad").val();
    var costo = $("#costo").val();
    var can_res = $("#can_res").val();
    var nitDistribuidor=$("#distribuidor option:selected").val();
    console.log(nitDistribuidor)
    data = {
      cod_Pedido: codigo,
      can_Total: cantidad,
      costoNeto: costo,
      can_res: can_res,
      distribuidoresA: {
        nitDistribuidor: nitDistribuidor
      }
    };
    $.ajax({
      url: "http://localhost:8080/agregarPedidos",
      type: "POST",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function () {
        $("#codigo").val("");
        $("#fechapedido").val("");
        $("#horapedido").val("");
        $("#can_res").val("");
        $("#cantidad").val("");
        $("#costo").val("");
        $("#nitDistribuidor").val("");
        buscarTodosLosPedidos();
      },
      error: function (xhr) {
        if (xhr.status === 409) {
          alert("El código del pedido ya existe...");
        }
      },
    });
  }
  
  function cargarPedido(codigo) {
    $.ajax({
      url: "http://localhost:8080/unicopd/" + codigo,
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#codigo").val(respuesta.cod_Pedido);
        $("#agregar").prop("disabled", true);
        $("#actualizar").prop("disabled", false);
        $("#fechapedido").val(respuesta.fechapedido);
        $("#horapedido").val(respuesta.horapedido);
        $("#can_res").val(respuesta.can_res);       
        $("#cantidad").val(respuesta.can_Total);
        $("#costo").val(respuesta.costoNeto);
      },
    });
  }
  
  function actualizarPedido() {
    var codigo = $("#codigo").val();
    var cantidad = $("#cantidad").val();
    var costo = $("#costo").val();
    var can_res = $("#can_res").val();
    var nitDistribuidor=$("#distribuidor option:selected").val();
    data = {
      cod_Pedido: codigo,
      can_Total: cantidad,
      can_res: can_res,
      costoNeto: costo,
      distribuidoresA: {
        nitDistribuidor: nitDistribuidor
      }
    };
    $.ajax({
      url: "http://localhost:8080/actualizarPedidos",
      type: "PUT",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function () {
        $("#agregar").prop("disabled", false);
        $("#actualizar").prop("disabled", true);
        $("#codigo").val("");
        $("#fechapedido").val("");
        $("#horapedido").val("");
        $("#can_res").val("");
        $("#cantidad").val("");
        $("#costo").val("");
        $("#nitDistribuidor").val("");
        buscarTodosLosPedidos();
      },
      error: function (xhr) {},
    });
  }
  
  function eliminarPedido(codigo) {
    $("#eliminar").off("click").on("click", function(){
      $.ajax({
        url: "http://localhost:8080/eliminarpedidoc/" + codigo,
        type: "DELETE",
        success: function () {
          $("#exampleModal").modal("hide");
          buscarTodosLosPedidos();
        },
      });
    });
  }



  $(document).ready(function() {
    let selectDistribuidores = document.querySelector('#distribuidor')
    selectDistribuidores.innerHTML = ''
    $.ajax({
        url: "http://localhost:8080/listardis",
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            for(i=0;i<respuesta.length;i++){
                selectDistribuidores.innerHTML += '<option value="' +respuesta[i].nitDistribuidor +'">'
                + respuesta[i].nitDistribuidor+'  '
                + respuesta[i].nomDistribuidor +'</option>'; 
            }
        }
    });

  });
  