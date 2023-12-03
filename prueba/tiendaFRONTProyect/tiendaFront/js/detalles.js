let valueProduct
let products
function buscarTodosLosDetalles() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
        url: "http://localhost:8080/listardetalles",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            for (i = 0; i < respuesta.length; i++) {
                tabla.innerHTML +=
                    '<tr><td>' +
                    respuesta[i].cod_Identificacion +
                    '</td><td>' +
                    respuesta[i].cantidad +
                    '</td><td>' +
                    respuesta[i].precio +
                    '</td><td>' +
                    respuesta[i].pago +
                    '</td><td>' +
                    respuesta[i].codigodetalle +
                    '</td><td>' +
                    respuesta[i].pedidos_productos.cod +
                    '</td><td>' +
                    "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarDetalle(\"" +
                    respuesta[i].cod_Identificacion +
                    "\")'> Eliminar</a> <a href='#'  onclick='cargarDetalle(\"" +
                    respuesta[i].cod_Identificacion +
                    "\")'> Editar</a>" +
                    '</td></tr>';
            }
        },
    });
}

function buscarDetallePorId() {
    var tabla = document.querySelector("#tabla");
    var codigo = $("#porId").val();
    $.ajax({
        url: "http://localhost:8080/unicodetallecod/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            tabla.innerHTML +=
                '<tr><td>' +
                respuesta.cod_Identificacion +
                '</td><td>' +
                respuesta.codigodetalle +
                '</td><td>' +
                respuesta.cantidad +
                '</td><td>' +
                respuesta.precio +
                '</td><td>' +
                respuesta.pago +
                '</td><td>' +
                respuesta.pedidos_productos.cod +
                '</td><td>' +
                "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarDetalle(\"" +
                respuesta.cod_Identificacion +
                "\")'> Eliminar</a> <a href='#'  onclick='cargarDetalle(\"" +
                respuesta.cod_Identificacion +
                "\")'> Editar</a>" +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
              alert("El codigo de identifacion no existe...");
            }
        },
    });
}

function insertarDetalle() {
    var cantidad = $("#cantidad").val();
    var precio = $("#precio").val();
    var pago = $("#pago").val();
    var codigodetalle = $("#codigodetalle").val();
    var cod = $("#cod option:selected").val();
 

    data = {
        cantidad: cantidad,
        precio: precio,
        pago: pago,
        codigodetalle: codigodetalle,
        pedidos_productos: {
            cod: cod
        }
    };

    $.ajax({
        url: "http://localhost:8080/agregarDetalle",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#cantidad").val("");
            $("#precio").val("");
            $("#pago").val("");
            $("#codigodetalle").val("");
            $("#cod").val("");
            buscarTodosLosDetalles();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                alert("El código de detalle ya existe...");
            }
        },
    });
}

function cargarDetalle(codigo) {
    $.ajax({
        url: "http://localhost:8080/unicodetallecod/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#codigoIdentificacion").val(respuesta.cod_Identificacion)
            $("#cantidad").val(respuesta.cantidad);
            $("#precio").val(respuesta.precio);
            $("#pago").val(respuesta.pago);
            $("#codigodetalle").val(respuesta.codigodetalle);
            $("#cod").val(respuesta.pedidos_productos.cod).attr('selected', 'selected');
            $("#agregar").prop("disabled", true);
            $("#actualizar").prop("disabled", false);
        },
    });
}

function actualizarDetalle() {
    var cod_Identificacion= $("#codigoIdentificacion").val()
    var cantidad = $("#cantidad").val();
    var precio = $("#precio").val();
    var pago = $("#pago").val();
    var codigodetalle = $("#codigodetalle").val();
    var cod = $("#cod option:selected").val();
    data = {
        cod_Identificacion: cod_Identificacion,
        cantidad: cantidad,
        precio: precio,
        codigodetalle: codigodetalle,
        pago: pago,
        pedidos_productos: {
            cod: cod
        }
    };

    $.ajax({
        url: "http://localhost:8080/actualizarDetalles",
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#agregar").prop("disabled", false);
            $("#actualizar").prop("disabled", true);
            $("#codigoIdentificacion").val("")
            $("#codigodetalle").val("");
            $("#cantidad").val("");
            $("#precio").val("");
            $("#pago").val("");
            $("#cod").val("");
            buscarTodosLosDetalles();
        },
        error: function (xhr) {},
    });
}

function eliminarDetalle(codigo) {
    $("#eliminar").off("click").on("click", function () {
        $.ajax({
            url: "http://localhost:8080/eliminardetalle/" + codigo,
            type: "DELETE",
            success: function () {
                $("#exampleModal").modal("hide");
                buscarTodosLosDetalles();
            },
        });
    });
}

$(document).ready(function () {
    let selectcod = document.querySelector('#cod')

    $.ajax({
        url: "http://localhost:8080/listarprodc",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
        products = respuesta
            for (i = 0; i < respuesta.length; i++) {
                selectcod.innerHTML += '<option value="' + respuesta[i].cod_Producto  + '">' +
                    respuesta[i].cod_Producto +' '+respuesta[i].nomProducto+ '</option>';
            }
        }
    });

    $.ajax({
        url: "http://localhost:8080/listarcomprar",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            for (i = 0; i < respuesta.length; i++) {
                selectCodCompra.innerHTML += '<option value="' + respuesta[i].cod_Compra + '">' +
                    respuesta[i].cod_Compra +' '+respuesta[i].dEstablecimiento+' '+respuesta[i].metodoPago+ '</option>';
            }
        }
    });
});

$('#cod').change(function() {
    var cod = $("#cod option:selected").val();
    $.ajax({
      url: `http://localhost:8080/precioproducto/${cod}`, 
      type: 'GET', 
      success: function(response) {
        $("#preciopr").val(response);
      },
      error: function(error) {
        console.error('Error en la solicitud AJAX:', error);
      }
    }); 
  });
  $('#cantidad').change(function() {
    var inputValue = $('#cantidad').val();
    var inputValue2 = $('#preciopr').val();
    let resultado= inputValue*inputValue2
    $('#precio').val(resultado);   
  });
function calcularPrecio(){
    asignarPrecio()
    let valor = 3000
    const cantidad = document.getElementById("cantidad").value;
    const precio = document.getElementById("precio");
    precio.value = parseFloat(cantidad)* parseFloat(valueProduct);
}
function asignarPrecio(){
    const select = document.getElementById("cod");
    console.log(products)
    const cod_Producto = select.options[select.selectedIndex].value;
    const product = products.find((product,index) => product.cod_Producto == cod_Producto )
    if(product){
        valueProduct = product.precio
    }
}