function buscarTodosLasDevoluciones() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
        url: "http://localhost:8080/listardevoluciones",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            for (i = 0; i < respuesta.length; i++) {
                tabla.innerHTML +=
                    '<tr><td>' +
                    respuesta[i].cod_Devolucion +
                    '</td><td>' +
                    respuesta[i].unidades +
                    '</td><td>' +
                    respuesta[i].detalles.cod_Identificacion +
                    '</td><td>' +
                    "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarDevolucion(\"" +
                    respuesta[i].cod_Devolucion +
                    "\")'> Eliminar</a> <a href='#'  onclick='cargarDevolucion(\"" +
                    respuesta[i].cod_Devolucion +
                    "\")'> Editar</a>" +
                    '</td></tr>';
            }
        },
    });
}

function buscarDevolucionPorId() {
    var tabla = document.querySelector("#tabla");
    var codigo = $("#porId").val();
    $.ajax({
        url: "http://localhost:8080/unicodevol/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            tabla.innerHTML +=
                '<tr><td>' +
                respuesta.cod_Devolucion +
                '</td><td>' +
                respuesta.unidades +
                '</td><td>' +
                respuesta.detalles.cod_Identificacion +
                '</td><td>' +
                "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarDevolucion(\"" +
                respuesta.cod_Devolucion +
                "\")'> Eliminar</a> <a href='#'  onclick='cargarDevolucion(\"" +
                respuesta.cod_Devolucion +
                "\")'> Editar</a>" +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
              alert("El codigo de devolucion no existe...");
            }
        },
    });
}

function insertarDevolucion() {
    var unidades = $("#unidades").val();
    var codIdentificacion = $("#codIdentificacion option:selected").val();

    data = {
        unidades: unidades,
        detalles: {
            cod_Identificacion: codIdentificacion
        }
    };

    $.ajax({
        url: "http://localhost:8080/agregarDevolucion",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#unidades").val("");
            $("#codIdentificacion").val("");
            buscarTodosLasDevoluciones();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                alert("El código de devolución ya existe...");
            }
        },
    });
}

function cargarDevolucion(codigo) {
    $.ajax({
        url: "http://localhost:8080/unicodevol/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#codDevolucion").val(respuesta.cod_Devolucion)
            $("#unidades").val(respuesta.unidades);
            $("#codIdentificacion").val(respuesta.detalles.cod_Identificacion).attr('selected', 'selected');
            $("#agregar").prop("disabled", true);
            $("#actualizar").prop("disabled", false);
        },
    });
}

function actualizarDevolucion() {
    var cod_Devolucion = $("#codDevolucion").val()
    var unidades = $("#unidades").val();
    var codIdentificacion = $("#codIdentificacion option:selected").val();

    data = {
        cod_Devolucion: cod_Devolucion,
        unidades: unidades,
        detalles: {
            cod_Identificacion: codIdentificacion
        }
    };

    $.ajax({
        url: "http://localhost:8080/actualizarDevoluciones",
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#agregar").prop("disabled", false);
            $("#actualizar").prop("disabled", true);
            $("#codDevolucion").val("")
            $("#unidades").val("");
            $("#codIdentificacion").val("");
            buscarTodosLasDevoluciones();
        },
        error: function (xhr) {},
    });
}

function eliminarDevolucion(codigo) {
    $("#eliminar").off("click").on("click", function () {
        $.ajax({
            url: "http://localhost:8080/eliminardevolucion/" + codigo,
            type: "DELETE",
            success: function () {
                $("#exampleModal").modal("hide");
                buscarTodosLasDevoluciones();
            },
        });
    });
}

$(document).ready(function () {
    let selectCodIdentificacion = document.querySelector('#codIdentificacion')

    selectCodIdentificacion.innerHTML = ''

    $.ajax({
        url: "http://localhost:8080/listardetalles",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            for (i = 0; i < respuesta.length; i++) {
                selectCodIdentificacion.innerHTML += '<option value="' + respuesta[i].cod_Identificacion + '">' +
                    respuesta[i].cod_Identificacion + '</option>';
            }
        },
        
    });
});
