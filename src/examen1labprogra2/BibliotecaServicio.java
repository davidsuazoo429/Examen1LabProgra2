/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examen1labprogra2;

/**
 *
 * @author David Suazo Palao
 */
import java.util.*;
import java.util.function.Predicate;

public class BibliotecaServicio {
    private List<MaterialBibliografico> materiales;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public BibliotecaServicio() {
        this.materiales = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        cargarDatosIniciales();
    }

    public void registrarMaterial(MaterialBibliografico m) { materiales.add(m); }
    public void registrarUsuario(Usuario u) { usuarios.add(u); }

    public Prestamo prestarMaterial(String codMaterial, String idUsuario, Date fecha) throws BibliotecaException {
        MaterialBibliografico mat = buscarMaterialExacto(codMaterial, 0);
        Usuario usu = buscarUsuarioPorId(idUsuario);

        if (mat == null) throw new BibliotecaException("Material con código '" + codMaterial + "' no encontrado.");
        if (usu == null) throw new BibliotecaException("Usuario con ID '" + idUsuario + "' no encontrado.");

        if (usu.estaPenalizado(fecha)) {
            throw new UsuarioPenalizadoException("El usuario está penalizado hasta: " + usu.getFinPenalizacion());
        }
        if (!usu.AccedeaComplejidad(mat.getNivelComplejidad())) {
            throw new AccesoNoAutorizadoException("Usuario estándar no tiene permisos para materiales de nivel ALTO.");
        }
        if (usu.getMaterialesPrestado().size() >= usu.getLimitePrestamos()) {
            throw new LimitePrestamosSuperadoException("Límite de préstamos superado (" + usu.getLimitePrestamos() + " máx).");
        }
        if (mat.getEstado() == EstadoMaterial.PRESTADO) {
            throw new MaterialNoDisponibleException("El material ya se encuentra prestado.");
        }
        if (mat.getEstado() == EstadoMaterial.RESERVADO) {
            String sigId = mat.siguienteEnCola();
            if (sigId != null && !sigId.equals(usu.getId())) {
                mat.reservar(sigId); // Vuelve a encolar al que estaba primero
                throw new MaterialNoDisponibleException("El material está reservado para otro usuario en cola.");
            }
        }

        mat.prestar();
        usu.getMaterialesPrestado().add(mat);
        Prestamo p = new Prestamo(mat, usu, fecha);
        prestamos.add(p);
        usu.getHistorialPrestamo().add(p);
        return p;
    }

    public void devolverMaterial(String codMaterial, Date fechaDevolucion) throws BibliotecaException {
        MaterialBibliografico mat = buscarMaterialExacto(codMaterial, 0);
        if (mat == null) throw new BibliotecaException("Material no encontrado.");
        if (mat.getEstado() == EstadoMaterial.DISPONIBLE) throw new BibliotecaException("El material ya está disponible.");

        Prestamo pActivo = null;
        for (Prestamo p : prestamos) {
            if (p.getMaterial().getCodigo().equalsIgnoreCase(codMaterial) && !p.isDevuelto()) {
                pActivo = p;
                break;
            }
        }

        if (pActivo != null) {
            pActivo.registrarDevolucion(fechaDevolucion);
            Usuario u = pActivo.getUsuario();
            u.getMaterialesPrestado().remove(mat);

            if (pActivo.estaVencido(fechaDevolucion)) {
                long retraso = pActivo.CalcularDiasRetraso(fechaDevolucion);
                u.aplicarPenalizacion((int) (retraso * 2), fechaDevolucion);
            }
        }

        mat.devolver();
    }

    public void reservarMaterial(String codMaterial, String idUsuario) throws BibliotecaException {
        MaterialBibliografico mat = buscarMaterialExacto(codMaterial, 0);
        Usuario usu = buscarUsuarioPorId(idUsuario);

        if (mat == null || usu == null) throw new BibliotecaException("Material o Usuario inválido.");
        if (mat.isDisponible()) throw new BibliotecaException("El material está disponible, tómelo en préstamo directo.");

        mat.reservar(usu.getId());
    }

    // Búsqueda Recursiva Exacta (Código o Título)
    public MaterialBibliografico buscarMaterialExacto(String criterio, int index) {
        if (materiales == null || index >= materiales.size()) return null;
        MaterialBibliografico actual = materiales.get(index);
        if (actual.getCodigo().equalsIgnoreCase(criterio) || actual.getTitulo().equalsIgnoreCase(criterio)) {
            return actual;
        }
        return buscarMaterialExacto(criterio, index + 1);
    }

    // Búsqueda Recursiva Flexible por Nivel
    public List<MaterialBibliografico> buscarMaterialFlexible(NivelComplejidad nivel, int index, List<MaterialBibliografico> acumulador) {
        if (materiales == null || index >= materiales.size()) return acumulador;
        MaterialBibliografico actual = materiales.get(index);
        if (actual.getNivelComplejidad() == nivel) {
            acumulador.add(actual);
        }
        return buscarMaterialFlexible(nivel, index + 1, acumulador);
    }

    // Cálculo Recursivo de Penalización Acumulada
    public long calcularDiasPenalizacionRecursivo(List<Prestamo> historial, int index, Date fechaRef) {
        if (historial == null || index >= historial.size()) return 0;
        Prestamo p = historial.get(index);
        long dias = p.CalcularDiasRetraso(fechaRef);
        return dias + calcularDiasPenalizacionRecursivo(historial, index + 1, fechaRef);
    }

    // Genérico para filtrar por clase
    public <T extends MaterialBibliografico> List<T> filtrarPorTipo(Class<T> tipo) {
        List<T> resultado = new ArrayList<>();
        for (MaterialBibliografico m : materiales) {
            if (tipo.isInstance(m)) {
                resultado.add(tipo.cast(m));
            }
        }
        return resultado;
    }

    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(id)) return u;
        }
        return null;
    }

    public List<MaterialBibliografico> getMateriales() { return materiales; }
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Prestamo> getPrestamos() { return prestamos; }

    private void cargarDatosIniciales() {
        usuarios.add(new UsuarioEstandar("001", "David Suazo"));
        usuarios.add(new UsuarioPremium("002", "Ana Martínez"));
        

        // Libros
        materiales.add(new Libro("La Divina Comedia", "LIB-001", NivelComplejidad.ALTO, "Portadas/LaDivinaComedia.jpg", "Dante Alighieri", 580, "978-8420665481"));
        materiales.add(new Libro("La Odisea", "LIB-002", NivelComplejidad.MEDIO, "Portadas/LaOdisea.jpg", "Homero", 448, "978-8420674209"));
        materiales.add(new Libro("La Iliada", "LIB-003", NivelComplejidad.ALTO, "Portadas/LaIliada.jpg", "Homero", 560, "978-8420674193"));
        materiales.add(new Libro("El Corazon Delator", "LIB-004", NivelComplejidad.BAJO, "Portadas/ElCorazonDelator.jpg", "Edgar Allan Poe", 64, "978-8415618751"));
        materiales.add(new Libro("La Caida de la Casa Usher", "LIB-005", NivelComplejidad.BAJO, "Portadas/LaCasaUsher.jpg", "Edgar Allan Poe", 80, "978-8415618768"));
        materiales.add(new Libro("Harry Potter y la Piedra Filosofal", "LIB-006", NivelComplejidad.BAJO, "Portadas/HarryPotterPFilosofal.jpg", "JK Rowling", 288, "978-8478884452"));

        // Revistas
        materiales.add(new Revista("National Geographic", "REV-001", NivelComplejidad.BAJO, "Portadas/NationalGeographic.jpg", 245, Periodicidad.MENSUAL));
        materiales.add(new Revista("Time", "REV-002", NivelComplejidad.MEDIO, "Portadas/Time_revista.jpg", 1050, Periodicidad.SEMANAL));
        materiales.add(new Revista("Cosmopolitan", "REV-003", NivelComplejidad.BAJO, "Portadas/cosmopolitan.jpg", 312, Periodicidad.MENSUAL));
        materiales.add(new Revista("People Magazine", "REV-004", NivelComplejidad.BAJO, "Portadas/PeopleMagazine.jpg", 520, Periodicidad.SEMANAL));
        materiales.add(new Revista("The Lancet", "REV-005", NivelComplejidad.ALTO, "Portadas/TheLancetRevista.jpg", 884, Periodicidad.SEMANAL));

        // Audiovisual
        materiales.add(new Audiovisual("Interstellar", "MED-001", NivelComplejidad.ALTO, "Portadas/Interstellar.jpg", 169, FormatoAudiovisual.BLURAY));
        materiales.add(new Audiovisual("Spiderman 2", "MED-002", NivelComplejidad.BAJO, "Portadas/Spiderman2.jpg", 127, FormatoAudiovisual.DVD));
        materiales.add(new Audiovisual("The Lion King", "MED-003", NivelComplejidad.BAJO, "Portadas/TheLionKing.jpg", 88, FormatoAudiovisual.DVD));
        materiales.add(new Audiovisual("Avatar", "MED-004", NivelComplejidad.MEDIO, "Portadas/Avatar.jpg", 162, FormatoAudiovisual.BLURAY));
        materiales.add(new Audiovisual("La La Land", "MED-005", NivelComplejidad.MEDIO, "Portadas/Lalaland.jpg", 128, FormatoAudiovisual.BLURAY));
    }
}
