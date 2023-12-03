function buscarTodosLosProductos() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
        url: "http://localhost:8080/listarprodc",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            for (i = 0; i < respuesta.length; i++) {
                tabla.innerHTML +=
                    '<tr><td>' +
                    respuesta[i].cod_Producto +
                    '</td><td>' +
                    respuesta[i].nomProducto +
                    '</td><td>' +
                    respuesta[i].pedidos.cod_Pedido +
                    '</td><td>' +
                    "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarProducto(\"" +
                    respuesta[i].cod_Producto +
                    "\")'> Eliminar</a> <a href='#'  onclick='cargarProducto(\"" +
                    respuesta[i].cod_Producto +
                    "\")'> Editar</a>" +
                    '</td></tr>';
            }
        },
    });
}

function buscarTodosLosProductosConPedidos() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
        url: "http://localhost:8080/productosEnPedidos",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            for (i = 0; i < respuesta.length; i++) {
                tabla.innerHTML +=
                    '<tr><td>' +
                    respuesta[i][0]+
                    '</td><td>' +
                    respuesta[i][1]+
                    '</td><td>' +
                    respuesta[i][2]+ 
                    '</td><td>' +
                    " "+ 
                    '</td></tr>';
            }
        },
    });
}

function buscarProductoPorId() {
    var tabla = document.querySelector("#tabla");
    var codigo = $("#porId").val();
    $.ajax({
        url: "http://localhost:8080/unicoprodc/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            tabla.innerHTML +=
                '<tr><td>' +
                respuesta.cod_Producto +
                '</td><td>' +
                respuesta.nomProducto +
                '</td><td>' +
                respuesta.pedidos.cod_Pedido +
                '</td><td>' +
                "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarProducto(\"" +
                respuesta.cod_Producto +
                "\")'> Eliminar</a> <a href='#'  onclick='cargarProducto(\"" +
                respuesta.cod_Producto +
                "\")'> Editar</a>" +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
              alert("El codigo de producto no existe...");
            }
        },
    });
}

function insertarProducto() {
    var codigo = $("#codigo").val();
    var nombre = $("#nombre").val();
    var codigoPedido = $("#codigoPedido option:selected").val();
    data = {
        cod_Producto: codigo,
        nomProducto: nombre,
        pedidos: {
            cod_Pedido: codigoPedido
        }
    };
    $.ajax({
        url: "http://localhost:8080/agregarProductos",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#codigo").val("");
            $("#nombre").val("");
            $("#codigoPedido").val("");
            buscarTodosLosProductos();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                alert("El código del producto ya existe...");
            }
        },
    });
}

function cargarProducto(codigo) {
    $.ajax({
        url: "http://localhost:8080/unicoprodc/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#codigo").val(respuesta.cod_Producto);
            $("#codigo").prop("disabled", true);
            $("#agregar").prop("disabled", true);
            $("#actualizar").prop("disabled", false);
            $("#nombre").val(respuesta.nomProducto);
            
            // Establecer la opción seleccionada en el campo "codigoPedido"
            $("#codigoPedido").val(respuesta.pedidos.cod_Pedido).attr('selected', 'selected');
        },
    });
}

function actualizarProducto() {
    var codigo = $("#codigo").val();
    var nombre = $("#nombre").val();
    var codigoPedido = $("#codigoPedido option:selected").val();
    data = {
        cod_Producto: codigo,
        nomProducto: nombre,
        pedidos: {
            cod_Pedido: codigoPedido
        }
    };
    $.ajax({
        url: "http://localhost:8080/actualizarProductos",
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#agregar").prop("disabled", false);
            $("#actualizar").prop("disabled", true);
            $("#codigo").val("");
            $("#nombre").val("");
            $("#codigoPedido").val("");
            buscarTodosLosProductos();
        },
        error: function (xhr) {},
    });
}

function eliminarProducto(codigo) {
    $("#eliminar").off("click").on("click", function(){
        $.ajax({
            url: "http://localhost:8080/eliminarproductom/" + codigo,
            type: "DELETE",
            success: function () {
                $("#exampleModal").modal("hide");
                buscarTodosLosProductos();
            },
        });
    });
}

$(document).ready(function() {
    let selectPedidos = document.querySelector('#codigoPedido')
    selectPedidos.innerHTML = ''
    $.ajax({
        url: "http://localhost:8080/listarpd",
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            for(i=0;i<respuesta.length;i++){
                selectPedidos.innerHTML += '<option value="' +respuesta[i].cod_Pedido +'">'
                + respuesta[i].cod_Pedido+'  '
                + respuesta[i].fechapedido+'  '
                + '</option>'; 
            }
        }
    });
});
