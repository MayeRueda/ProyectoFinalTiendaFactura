function buscarTodosLosEmpleados() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
      url: "http://localhost:8080/listarem",
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#tabla tbody").remove();
        for (i = 0; i < respuesta.length; i++) {
          tabla.innerHTML +=
            '<tr><td>' +
            respuesta[i].cc_Empleado +
            '</td><td>' +
            respuesta[i].nomEmpleado +
            '</td><td>' +
            respuesta[i].salario_Empleado +
            '</td><td>' +
            respuesta[i].horario +
            '</td><td>' +
            respuesta[i].cargo +
            '</td><td>' +
            "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarEmpleado(\"" +
            respuesta[i].cc_Empleado +
            "\")'> Eliminar</a> <a href='#'  onclick='cargarEmpleado(\"" +
            respuesta[i].cc_Empleado +
            "\")'> Editar</a>" +
            '</td></tr>';
        }
      },
    });
  }

  function buscarTodosLosEmpleadosConComprar() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
      url: "http://localhost:8080/listarEmpleadosConComprar",
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#tabla tbody").remove();
        for (i = 0; i < respuesta.length; i++) {
          tabla.innerHTML +=
            '<tr><td>' +
            respuesta[i][0]+
            '</td><td>' +
            respuesta[i][3] +
            '</td><td>' +
            respuesta[i][4] +
            '</td><td>' +
            respuesta[i][2] +
            '</td><td>' +
            respuesta[i][1] +
            '</td><td>' +
            "Total venta: "+respuesta[i][5] +
            '</td></tr>';
        }
      },
    });
  }
  
  function buscarEmpleadoPorId() {
    var tabla = document.querySelector("#tabla");
    var cedula = $("#porId").val();
    $.ajax({
      url: "http://localhost:8080/unicoem/" + cedula,
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#tabla tbody").remove();
        tabla.innerHTML +=
          '<tr><td>' +
          respuesta.cc_Empleado +
          '</td><td>' +
          respuesta.nomEmpleado +
          '</td><td>' +
          respuesta.salario_Empleado +
          '</td><td>' +
          respuesta.horario +
          '</td><td>' +
          respuesta.cargo +
          '</td><td>' +
          "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarEmpleado(\"" +
          respuesta.cc_Empleado +
          "\")'> Eliminar</a> <a href='#'  onclick='cargarEmpleado(\"" +
          respuesta.cc_Empleado +
          "\")'> Editar</a>" +
          '</td></tr>';
      },
      error: function (xhr) {
        if (xhr.status === 404) {
          alert("La cedula no existe...");
        }
    },
    });
  }
  
  function insertarEmpleado() {
    var cedula = $("#cedula").val();
    var nombre = $("#nombre").val();
    var salario = $("#salario").val();
    var horario = $("#horario").val();
    var cargo = $("#cargo").val();
    data = {
      cc_Empleado: cedula,
      nomEmpleado: nombre,
      salario_Empleado: salario,
      horario: horario,
      cargo: cargo,
    };
    $.ajax({
      url: "http://localhost:8080/insertarem",
      type: "POST",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function () {
        $("#cedula").val("");
        $("#nombre").val("");
        $("#salario").val("");
        $("#horario").val("");
        $("#cargo").val("");
        buscarTodosLosEmpleados();
      },
      error: function (xhr) {
        if (xhr.status === 409) {
          alert("La cédula del empleado ya existe...");
        }
      },
    });
  }
  
  function cargarEmpleado(cedula) {
    $.ajax({
      url: "http://localhost:8080/unicoem/" + cedula,
      type: "GET",
      dataType: "json",
      success: function (respuesta) {
        $("#cedula").val(respuesta.cc_Empleado);
        $("#cedula").prop("disabled", true);
        $("#agregar").prop("disabled", true);
        $("#actualizar").prop("disabled", false);
        $("#nombre").val(respuesta.nomEmpleado);
        $("#salario").val(respuesta.salario_Empleado);
        $("#horario").val(respuesta.horario);
        $("#cargo").val(respuesta.cargo);
      },
    });
  }
  
  function actualizarEmpleado() {
    var cedula = $("#cedula").val();
    var nombre = $("#nombre").val();
    var salario = $("#salario").val();
    var horario = $("#horario").val();
    var cargo = $("#cargo").val();
    data = {
      cc_Empleado: cedula,
      nomEmpleado: nombre,
      salario_Empleado: salario,
      horario: horario,
      cargo: cargo,
    };
    $.ajax({
      url: "http://localhost:8080/actualizaremSave",
      type: "PUT",
      data: JSON.stringify(data),
      contentType: "application/json",
      success: function () {
        $("#cedula").prop("disabled", false);
        $("#agregar").prop("disabled", false);
        $("#actualizar").prop("disabled", true);
        $("#cedula").val("");
        $("#nombre").val("");
        $("#salario").val("");
        $("#horario").val("");
        $("#cargo").val("");
        buscarTodosLosEmpleados();
      },
      error: function (xhr) {},
    });
  }
  
  function eliminarEmpleado(cedula) {
    $("#eliminar").off("click").on("click", function(){
      $.ajax({
        url: "http://localhost:8080/eliminarem/" + cedula,
        type: "DELETE",
        success: function () {
          $("#exampleModal").modal("hide");
          buscarTodosLosEmpleados();
        },
      });
    });
  }
  