create database tienda1;


use tienda1;

DELIMITER //
CREATE TRIGGER set_fecha_hora_comprar
BEFORE INSERT ON comprar
FOR EACH ROW
BEGIN
    SET NEW.fecha = CURDATE();
    SET NEW.hora = CURTIME();
END;
//
DELIMITER //
CREATE TRIGGER calcular_vueltas
BEFORE INSERT ON comprar
FOR EACH ROW
BEGIN
    SET NEW.total_compra = (
        SELECT SUM(precio * cantidad)
        FROM detalles
        WHERE cod_identificacion = NEW.codigodetalle
    );

    IF NEW.pago IS NOT NULL AND NEW.total_compra IS NOT NULL THEN
        SET NEW.vueltas = NEW.pago - NEW.total_compra;
    ELSE
        SET NEW.vueltas = 0;  
    END IF;
END;
//



DELIMITER //

CREATE TRIGGER ActualizarPrecioYPagoDetalle
BEFORE INSERT ON detalles
FOR EACH ROW
BEGIN
    DECLARE precio_producto INT;

    SELECT precios INTO precio_producto
    FROM pedidos_productos
    WHERE cod = NEW.cod;

    SET NEW.precio = precio_producto;

    SET NEW.pago = NEW.cantidad * NEW.precio;

END;
//

DELIMITER ;


DELIMITER //
CREATE TRIGGER set_fecha_hora_pedido
BEFORE INSERT ON pedidos
FOR EACH ROW
BEGIN
    SET NEW.fechapedido = CURDATE();
    SET NEW.horapedido = CURTIME();
END;
//
DELIMITER ;
DELIMITER //

DELIMITER //
CREATE TRIGGER ActualizarCantidadReservada
AFTER INSERT ON pedidos_productos
FOR EACH ROW
BEGIN
    DECLARE suma_cantidad INT;

    SELECT IFNULL(SUM(cantidad), 0) INTO suma_cantidad
    FROM pedidos_productos
    WHERE cod_Pedido = NEW.cod_Pedido;

    UPDATE pedidos
    SET can_res = suma_cantidad
    WHERE cod_Pedido = NEW.cod_Pedido;
END;
//
DELIMITER ;

SHOW TRIGGERS;

DROP TRIGGER IF EXISTS calcular_vueltas;