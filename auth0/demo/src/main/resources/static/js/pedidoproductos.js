function buscarTodosLosPedidoProductos() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
        url: "http://localhost:8080/listarprodcpds",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            for (i = 0; i < respuesta.length; i++) {
                tabla.innerHTML +=
                    '<tr><td>' +
                    respuesta[i].cod +
                    '</td><td>' +
                    respuesta[i].cantidad +
                    '</td><td>' +
                    respuesta[i].precios +
                    '</td><td>' +
                    respuesta[i].pedidos.cod_Pedido +
                    '</td><td>' +
                    respuesta[i].productos.cod_Producto +
                    '</td><td>' +
                    "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarPedidoProducto(\"" +
                    respuesta[i].cod +
                    "\")'> Eliminar</a> <a href='#'  onclick='cargarPedidoProducto(\"" +
                    respuesta[i].cod +
                    "\")'> Editar</a>" +
                    '</td></tr>';
            }
        },
    });
}

function buscarPedidoProductoPorId() {
    var tabla = document.querySelector("#tabla");
    var codigo = $("#porId").val();
    $.ajax({
        url: "http://localhost:8080/unicoprodcpedido/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            tabla.innerHTML +=
                '<tr><td>' +
                respuesta.cod +
                '</td><td>' +
                respuesta.cantidad +
                '</td><td>' +
                respuesta.precios +
                '</td><td>' +
                respuesta.pedidos.cod_Pedido +
                '</td><td>' +
                respuesta.productos.cod_Producto +
                '</td><td>' +
                "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarPedidoProducto(\"" +
                respuesta.cod +
                "\")'> Eliminar</a> <a href='#'  onclick='cargarPedidoProducto(\"" +
                respuesta.cod +
                "\")'> Editar</a>" +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
              alert("El cod no existe...");
            }
        },
    });
}

function insertarPedidoProducto() {
    var codigo = $("#codigo").val();
    var cantidad = $("#cantidad").val();
    var precio = $("#precio").val();
    var codigoPedido = $("#codigoPedido option:selected").val();
    var codigoProducto = $("#codigoProducto option:selected").val();
    
    data = {
        cod: codigo,
        cantidad: cantidad,
        precios: precio,
        pedidos: {
            cod_Pedido: codigoPedido
        },
        productos: {
            cod_Producto: codigoProducto
        }
    };
    
    $.ajax({
        url: "http://localhost:8080/agregarPedidosProductos",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#codigo").val("");
            $("#cantidad").val("");
            $("#precio").val("");
            $("#codigoPedido").val("");
            $("#codigoProducto").val("");
            buscarTodosLosPedidoProductos();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                alert("El código del Pedido Producto ya existe...");
            }
        },
    });
}

function cargarPedidoProducto(codigo) {
    $.ajax({
        url: "http://localhost:8080/unicoprodcpedido/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#codigo").val(respuesta.cod);
            $("#codigo").prop("disabled", true);
            $("#agregar").prop("disabled", true);
            $("#actualizar").prop("disabled", false);
            $("#cantidad").val(respuesta.cantidad);
            $("#precio").val(respuesta.precios);
            
            // Establecer la opción seleccionada en el campo "codigoPedido"
            $("#codigoPedido").val(respuesta.pedidos.cod_Pedido).attr('selected', 'selected');
            
            // Establecer la opción seleccionada en el campo "codigoProducto"
            $("#codigoProducto").val(respuesta.productos.cod_Producto).attr('selected', 'selected');
        },
    });
}

function actualizarPedidoProducto() {
    var codigo = $("#codigo").val();
    var cantidad = $("#cantidad").val();
    var precio = $("#precio").val();
    var codigoPedido = $("#codigoPedido option:selected").val();
    var codigoProducto = $("#codigoProducto option:selected").val();
    
    data = {
        cod: codigo,
        cantidad: cantidad,
        precios: precio,
        pedidos: {
            cod_Pedido: codigoPedido
        },
        productos: {
            cod_Producto: codigoProducto
        }
    };
    
    $.ajax({
        url: "http://localhost:8080/actualizarPedidosProductos",
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#codigo").prop("disabled", false);
            $("#agregar").prop("disabled", false);
            $("#actualizar").prop("disabled", true);
            $("#codigo").val("");
            $("#cantidad").val("");
            $("#precio").val("");
            $("#codigoPedido").val("");
            $("#codigoProducto").val("");
            buscarTodosLosPedidoProductos();
        },
        error: function (xhr) {},
    });
}

function eliminarPedidoProducto(codigo) {
    $("#eliminar").off("click").on("click", function(){
        $.ajax({
            url: "http://localhost:8080/eliminarpedidoproductom/" + codigo,
            type: "DELETE",
            success: function () {
                $("#exampleModal").modal("hide");
                buscarTodosLosPedidoProductos();
            },
        });
    });
}

$(document).ready(function() {
    let selectPedidos = document.querySelector('#codigoPedido')
    let selectProductos = document.querySelector('#codigoProducto')
    
    selectPedidos.innerHTML = ''
    selectProductos.innerHTML = ''
    
    $.ajax({
        url: "http://localhost:8080/listarpd",
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            for(i = 0; i < respuesta.length; i++) {
                selectPedidos.innerHTML += '<option value="' + respuesta[i].cod_Pedido +'">'
                + respuesta[i].cod_Pedido + '</option>'; 
            }
        }
    });

    $.ajax({
        url: "http://localhost:8080/listarprodc",
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            for(i = 0; i < respuesta.length; i++) {
                selectProductos.innerHTML += '<option value="' + respuesta[i].cod_Producto +'">'
                + respuesta[i].cod_Producto+'  '
                + respuesta[i].nomProducto+'  '
                + '</option>'; 
            }
        }
    });
});
