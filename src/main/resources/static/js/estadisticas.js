document.addEventListener('DOMContentLoaded', function() {
    const ctxEmpresas = document.getElementById('chartEmpresas');
    const ctxCursos = document.getElementById('chartCursos');

    if (ctxEmpresas) {
        new Chart(ctxEmpresas, {
            type: 'bar',
            data: {
                labels: datosGraficas.nombresEmpresas, // Viene del Model
                datasets: [{
                    label: 'Alumnos Asignados',
                    data: datosGraficas.datosEmpresas, // Viene del Model
                    backgroundColor: 'rgba(13, 110, 253, 0.5)',
                    borderColor: 'rgb(13, 110, 253)',
                    borderWidth: 1
                }]
            }
        });
    }

    if (ctxCursos) {
        new Chart(ctxCursos, {
            type: 'pie',
            data: {
                labels: datosGraficas.nombresCursos,
                datasets: [{
                    data: datosGraficas.datosCursos,
                    backgroundColor: ['#0d6efd', '#6610f2', '#6f42c1', '#d63384']
                }]
            }
        });
    }
});