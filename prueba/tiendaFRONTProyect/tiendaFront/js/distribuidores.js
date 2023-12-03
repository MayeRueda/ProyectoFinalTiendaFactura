function buscarTodosLosDistribuidores() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
      url: "http://localhost:8080/listardis",
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#tabla tbody").remove();
        for (i = 0; i < respuesta.length; i++) {
          tabla.innerHTML +=
            '<tr><td>' +
            respuesta[i].nitDistribuidor +
            '</td><td>' +
            respuesta[i].nomDistribuidor +
            '</td><td>' +
            "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarDistribuidor(\"" +
            respuesta[i].nitDistribuidor +
            "\")'> Eliminar</a> <a href='#'  onclick='cargarDistribuidor(\"" +
            respuesta[i].nitDistribuidor +
            "\")'> Editar</a>" +
            '</td></tr>';
        }
      },
    });
  }
  
  function buscarDistribuidorPorId() {
    var tabla = document.querySelector("#tabla");
    var nit = $("#porId").val();
    $.ajax({
      url: "http://localhost:8080/unicodis/" + nit,
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#tabla tbody").remove();
        tabla.innerHTML +=
          '<tr><td>' +
          respuesta.nitDistribuidor +
          '</td><td>' +
          respuesta.nomDistribuidor +
          '</td><td>' +
          "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarDistribuidor(\"" +
          respuesta.nitDistribuidor +
          "\")'> Eliminar</a> <a href='#'  onclick='cargarDistribuidor(\"" +
          respuesta.nitDistribuidor +
          "\")'> Editar</a>" +
          '</td></tr>';
      },
      error: function (xhr) {
        if (xhr.status === 404) {
          alert("El NIT no existe...");
        }
      },
    });
  }
  
  function insertarDistribuidor() {
    var nit = $("#nit").val();
    var nombre = $("#nombre").val();
    data = {
      nitDistribuidor: nit,
      nomDistribuidor: nombre,
    };
    $.ajax({
      url: "http://localhost:8080/insertardis",
      type: "POST",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function () {
        $("#nit").val("");
        $("#nombre").val("");
        buscarTodosLosDistribuidores();
      },
      error: function (xhr) {
        if (xhr.status === 40) {
          alert("El NIT del distribuidor ya existe...");
        }
      },
    });
  }
  
  function cargarDistribuidor(nit) {
    $.ajax({
      url: "http://localhost:8080/unicodis/" + nit,
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#nit").val(respuesta.nitDistribuidor);
        $("#nit").prop("disabled", true);
        $("#agregar").prop("disabled", true);
        $("#actualizar").prop("disabled", false);
        $("#nombre").val(respuesta.nomDistribuidor);
      },
    });
  }
  
  function actualizarDistribuidor() {
    var nit = $("#nit").val();
    var nombre = $("#nombre").val();
    data = {
      nitDistribuidor: nit,
      nomDistribuidor: nombre,
    };
    $.ajax({
      url: "http://localhost:8080/actualizardisSave",
      type: "PUT",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function () {
        $("#nit").prop("disabled", false);
        $("#agregar").prop("disabled", false);
        $("#actualizar").prop("disabled", true);
        $("#nit").val("");
        $("#nombre").val("");
        buscarTodosLosDistribuidores();
      },
      error: function (xhr) {},
    });
  }
  
  function eliminarDistribuidor(nit) {
    $("#eliminar").off("click").on("click", function () {
      $.ajax({
        url: "http://localhost:8080/eliminardis/" + nit,
        type: "DELETE",
        success: function () {
          $("#exampleModal").modal("hide");
          buscarTodosLosDistribuidores();
        },
      });
    });
  }
  




