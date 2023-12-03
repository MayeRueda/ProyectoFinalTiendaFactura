let optionsDetails = []
function buscarTodosLasCompras() {
    var tabla = document.querySelector("#tabla");
    $.ajax({
        url: "http://localhost:8080/listarcomprar",
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            for (i = 0; i < respuesta.length; i++) {
                tabla.innerHTML +=
                    '<tr><td>' +
                    respuesta[i].cod_Compra +
                    '</td><td>' +
                    respuesta[i].fecha +
                    '</td><td>' +
                    respuesta[i].hora +
                    '</td><td>' +
                    respuesta[i].total_compra +
                    '</td><td>' +
                    respuesta[i].iva +
                    '</td><td>' +
                    respuesta[i].pago +
                    '</td><td>' +
                    respuesta[i].vueltas +
                    '</td><td>' +
                    respuesta[i].dEstablecimiento +
                    '</td><td>' +
                    respuesta[i].metodoPago +
                    '</td><td>' +
                    respuesta[i].clientes.cc_clientes +
                    '</td><td>' +
                    respuesta[i].empleados.cc_Empleado +
                    '</td><td>' +
                    respuesta[i].detalles.codigodetalle +
                    '</td><td>' +
                    "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarCompra(\"" +
                    respuesta[i].cod_Compra +
                    "\")'> Eliminar</a> <a href='#'  onclick='cargarCompra(\"" +
                    respuesta[i].cod_Compra +
                    "\")'> Editar</a>" +
                    '</td></tr>';
            }
        },
    });
}

function buscarCompraPorId() {
    var tabla = document.querySelector("#tabla");
    var codigo = $("#porId").val();
    $.ajax({
        url: "http://localhost:8080/unicocomprarcod/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#tabla tbody").remove();
            tabla.innerHTML +=
                '<tr><td>' +
                respuesta.cod_Compra +
                '</td><td>' +
                respuesta.fecha +
                '</td><td>' +
                respuesta.hora +
                '</td><td>' +
                respuesta.total_compra +
                '</td><td>' +
                respuesta.iva +
                '</td><td>' +
                respuesta.pago +
                '</td><td>' +
                respuesta.vueltas +
                '</td><td>' +
                respuesta.dEstablecimiento +
                '</td><td>' +
                respuesta.metodoPago +
                '</td><td>' +
                respuesta.clientes.cc_clientes +
                '</td><td>' +
                respuesta.empleados.cc_Empleado +
                '</td><td>' +
                respuesta.detalles.codigodetalle +
                    '</td><td>' +
                "<a id='textoEliminar' href='#' data-bs-toggle='modal' data-bs-target='#exampleModal'  onclick='eliminarCompra(\"" +
                respuesta.cod_Compra +
                "\")'> Eliminar</a> <a href='#'  onclick='cargarCompra(\"" +
                respuesta.cod_Compra +
                "\")'> Editar</a>" +
                '</td></tr>';
        },
        error: function (xhr) {
            if (xhr.status === 404) {
              alert("El codigo de compra no existe...");
            }
        },
    });
}

function insertarCompra() {
    var codigoCompra = $("#codigoCompra").val();
    var totalCompra = $("#totalCompra").val();
    var iva = $("#iva").val();
    var pago = $("#pago").val();
    var vueltas = $("#vueltas").val();
    var dEstablecimiento = $("#dEstablecimiento").val();
    var metodoPago = $("#metodoPago").val();
    var ccClientes = $("#ccClientes option:selected").val();
    var ccEmpleado = $("#ccEmpleado option:selected").val();
    var codigodetalle = $("#codigodetalle option:selected").val();
    data = {
        cod_Compra: codigoCompra,
        total_compra: totalCompra,
        iva: iva,
        pago: pago,
        vueltas: vueltas,
        dEstablecimiento: dEstablecimiento,
        metodoPago: metodoPago,
        clientes: {
            cc_clientes: ccClientes
        },
        empleados: {
            cc_Empleado: ccEmpleado
        },
        detalles: {
            codigodetalle: codigodetalle
        }
    };
    $.ajax({
        url: "http://localhost:8080/agregarCompras",
        type: "POST",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#codigoCompra").val("");
            $("#totalCompra").val("");
            $("#fecha").val("");
            $("#hora").val("");
            $("#iva").val("");
            $("#pago").val("");
            $("#vueltas").val("");
            $("#dEstablecimiento").val("");
            $("#metodoPago").val("");
            $("#ccClientes").val("");
            $("#ccEmpleado").val("");
            $("#codigodetalle").val("");
            buscarTodosLasCompras();
        },
        error: function (xhr) {
            if (xhr.status === 409) {
                alert("El código de compra ya existe...");
            }
        },
    });
}

function cargarCompra(codigo) {
    $.ajax({
        url: "http://localhost:8080/unicocomprarcod/" + codigo,
        type: "GET",
        dataType: "json",
        success: function (respuesta) {
            $("#codigoCompra").val(respuesta.cod_Compra);
            $("#codigoCompra").prop("disabled", true);
            $("#fecha").val(respuesta.fecha);
            $("#hora").val(respuesta.hora);
            $("#agregar").prop("disabled", true);
            $("#actualizar").prop("disabled", false);
            $("#totalCompra").val(respuesta.total_compra);
            $("#iva").val(respuesta.iva);
            $("#pago").val(respuesta.pago);
            $("#vueltas").val(respuesta.vueltas);
            $("#dEstablecimiento").val(respuesta.dEstablecimiento);
            $("#metodoPago").val(respuesta.metodoPago);
            $("#ccClientes").val(respuesta.clientes.cc_clientes).attr('selected', 'selected');
            $("#ccEmpleado").val(respuesta.empleados.cc_Empleado).attr('selected', 'selected');
            $("#codigodetalle").val(respuesta.detalles.codigodetalle).attr('selected', 'selected');
        },
    });
}

function actualizarCompra() {
    var codigoCompra = $("#codigoCompra").val();
    var totalCompra = $("#totalCompra").val();
    var iva = $("#iva").val();
    var pago = $("#pago").val();
    var vueltas = $("#vueltas").val();
    var dEstablecimiento = $("#dEstablecimiento").val();
    var metodoPago = $("#metodoPago").val();
    var ccClientes = $("#ccClientes option:selected").val();
    var ccEmpleado = $("#ccEmpleado option:selected").val();
    var codigodetalle = $("#codigodetalle option:selected").val();
    data = {
        cod_Compra: codigoCompra,
        total_compra: totalCompra,
        iva: iva,
        pago: pago,
        vueltas: vueltas,
        dEstablecimiento: dEstablecimiento,
        metodoPago: metodoPago,
        clientes: {
            cc_clientes: ccClientes
        },
        empleados: {
            cc_Empleado: ccEmpleado
        },
        detalles: {
            codigodetalle: codigodetalle
        }
    };
    $.ajax({
        url: "http://localhost:8080/actualizarCompras",
        type: "PUT",
        data: JSON.stringify(data),
        contentType: "application/json",
        success: function () {
            $("#agregar").prop("disabled", false);
            $("#actualizar").prop("disabled", true);
            $("#codigoCompra").val("");
            $("#fecha").val("");
            $("#hora").val("");
            $("#totalCompra").val("");
            $("#iva").val("");
            $("#pago").val("");
            $("#vueltas").val("");
            $("#dEstablecimiento").val("");
            $("#metodoPago").val("");
            $("#ccClientes").val("");
            $("#ccEmpleado").val("");
            $("#codigodetalle").val("");
            buscarTodosLasCompras();
        },
        error: function (xhr) {},
    });
}

function eliminarCompra(codigoCompra) {
    $("#eliminar").off("click").on("click", function(){
        $.ajax({
            url: "http://localhost:8080/eliminarcompra/" + codigoCompra,
            type: "DELETE",
            success: function () {
                $("#exampleModal").modal("hide");
                buscarTodosLasCompras();
            },
        });
    });
}

  $('#pago').change(function() {
    var inputValue = $('#totalCompra').val();
    var inputValue2 = $('#pago').val();
    let resultado= inputValue2-inputValue
    $('#vueltas').val(resultado);   
  }); 

$(document).ready(function() { 
    let selectClientes = document.querySelector('#ccClientes'); 
    selectClientes.innerHTML = ''; 
    $.ajax({
        url: "http://localhost:8080/listar",
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            for(i = 0; i < respuesta.length; i++){ 
                selectClientes.innerHTML += '<option value="' + respuesta[i].cc_clientes +'">' 
                + respuesta[i].cc_clientes + ' ' + respuesta[i].nomCliente
                + '</option>'; 
            }
        } 
    });
    let selectEmpleado = document.querySelector('#ccEmpleado'); 
    selectEmpleado.innerHTML = ''; 
    $.ajax({
        url: "http://localhost:8080/listarem", 
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            for(i = 0; i < respuesta.length; i++){ 
                selectEmpleado.innerHTML += '<option value="' + respuesta[i].cc_Empleado +'">' 
                + respuesta[i].cc_Empleado + ' ' + respuesta[i].nomEmpleado 
                + '</option>'; 
            }
        }
    });
    let selectDetalles = document.querySelector('#codigodetalle'); 
    $.ajax({
        url: "http://localhost:8080/listardetalles", 
        type: "GET",
        datatype: "JSON",
        success: function(respuesta) {
            optionsDetails = respuesta
            console.log(respuesta)
            for(i = 0; i < respuesta.length; i++){ 
                selectDetalles.innerHTML += '<option value="' + respuesta[i].cod_Identificacion +'">' 
                + respuesta[i].cod_Identificacion + ' ' + respuesta[i].codigodetalle
                + '</option>'; 
            }
        }
    });
});
function calculartotalcompra(){
    const select = document.querySelector("#codigodetalle");
    const totalCalculo = document.querySelector("#totalCompra");
    let totalCalculocompra = 0;
const optionsSelect = [...select.selectedOptions].map(option => option.value);
optionsDetails.forEach((detail)=>{
    if (optionsSelect.includes(detail.cod_Identificacion.toString())){
        totalCalculocompra += detail.pago;
        console.log(detail.pago);
    }
});
totalCalculo.value = totalCalculocompra;
console.log(optionsSelect);
}