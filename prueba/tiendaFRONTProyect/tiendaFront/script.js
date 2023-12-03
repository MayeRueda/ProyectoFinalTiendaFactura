const menuToggle = document.getElementById('menu-toggle');
const menuLateral = document.getElementById('menu-lateral');
const overlay = document.getElementById('overlay');
const contenido = document.querySelector('.contenido');

menuToggle.addEventListener('click', () => {
    if (menuLateral.style.left === '-250px') {
        menuLateral.style.left = '0';
        contenido.style.marginLeft = '250px';
        overlay.style.display = 'block';
    } else {
        menuLateral.style.left = '-250px';
        contenido.style.marginLeft = '0';
        overlay.style.display = 'none';
    }
});

overlay.addEventListener('click', () => {
    menuLateral.style.left = '-250px';
    contenido.style.marginLeft = '0';
    overlay.style.display = 'none';
});

// Evitar que el menú se cierre al hacer clic en los botones del menú
const menuItems = document.querySelectorAll('.menu-lateral a');

menuItems.forEach(item => {
    item.addEventListener('click', (event) => {
        event.preventDefault();
    });
});

function toggleDropdown() {
    const dropdown = document.getElementById('admin-options');
    dropdown.style.display = (dropdown.style.display === 'none' || dropdown.style.display === '') ? 'block' : 'none';
  }
  
  function mostraralertas() {
    alert('Alertas');
  }
  
  function mostrarPreguntasFrecuentes() {
    alert('Preguntas frecuentes');
  }
  
  function mostrarCerrarSesion() {
    alert('Cerrar sesión');
  }
  // Obtén una referencia a los botones por su clase
const empleadoButton = document.querySelector('.red-button');
const clienteButton = document.querySelector('.black-button');

// Agrega un manejador de eventos para redireccionar al hacer clic en los botones
empleadoButton.addEventListener('click', function() {
    window.location.href = 'empleados.html';
});

clienteButton.addEventListener('click', function() {
    window.location.href = 'clientes.html';
});

  // Obtén una referencia a los botones por su clase
  const pedidosButton = document.querySelector('.boton1-button');
  const distribuidoresButton = document.querySelector('.boton2-button');
  const pedidos_productosButton = document.querySelector('.boton3-button');
  const productosButton = document.querySelector('.boton4-button');
  
  // Agrega un manejador de eventos para redireccionar al hacer clic en los botones
  pedidosButton.addEventListener('click', function() {
      window.location.href = 'pedidos.html';
  });
  
  distribuidoresButton.addEventListener('click', function() {
      window.location.href = 'distribuidores.html';
  });

  pedidos_productosButton.addEventListener('click', function() {
    window.location.href = 'pedidoproducto.html';
});

productosButton.addEventListener('click', function() {
    window.location.href = 'productos.html';
});
  