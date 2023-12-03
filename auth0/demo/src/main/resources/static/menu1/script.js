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