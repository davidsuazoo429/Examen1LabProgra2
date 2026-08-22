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
    private List<MaterialBibliografico> catalogo;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;
    
    public BibliotecaServicio(){
        this.catalogo=new ArrayList <>();
        this.usuarios=new ArrayList <>();
        this.prestamos=new ArrayList <>();
        cargarDatosPrueba();
    }
    
    public void registrarMaterial(MaterialBibliografico m){
        catalogo.add(m);
    }
    public void registrarUsuario(Usuario u){
        usuarios.add(u);
    }
    
    public Prestamo prestarMaterial(String codMaterial, String idUsuario, Date fecha) throws BibliotecaException{
        MaterialBibliografico mat=buscarPorCodigo(codMaterial);
        Usuario usu=buscarUsuarioPorId(idUsuario);
        
        if(mat==null) throw new BibliotecaException("Material no encontrado");
        if(usu==null) throw new BibliotecaException("Usuario no econtrado");
        
        if (usu.estaPenalizado(fecha)){
            throw new UsuarioPenalizadoException("Usuario penalizado hasta: "+usu.getFinPenalizacion());
        }
        if (!usu.AccedeaComplejidad(mat.getNivelComplejidad())) {
            throw new AccesoNoAutorizadoException("El usuario estándar no tiene autorización para complejidad ALTA.");
        }
        if (usu.getMaterialesPrestado().size() >= usu.getLimitePrestamos()) {
            throw new LimitePrestamosSuperadoException("Límite máximo de préstamos alcanzado (" + usu.getLimitePrestamos() + ").");
        }
        if (mat.getEstado() == EstadoMaterial.PRESTADO) {
            throw new MaterialNoDisponibleException("El material ya se encuentra prestado.");
        }
        if (mat.getEstado() == EstadoMaterial.RESERVADO) {
            String siguiente = mat.siguienteEnCola();
            if (siguiente != null && !siguiente.equals(usu.getId())) {
                mat.reservar(siguiente);
                throw new MaterialNoDisponibleException("Material reservado para otro usuario.");
            }
        }
        
        mat.prestar();
        usu.getMaterialesPrestado().add(mat);
        Prestamo p=new Prestamo(mat,usu,fecha);
        prestamos.add(p);
        usu.getHistorialPrestamo().add(p);
        return p;
    }
    
    public void devolverMaterial(String codMaterial, Date fechaDevolucion)throws BibliotecaException{
        MaterialBibliografico mat=buscarPorCodigo(codMaterial);
        if (mat.getEstado() == EstadoMaterial.DISPONIBLE) throw new BibliotecaException("El material no está prestado.");
        if (mat == null) throw new BibliotecaException("Material no encontrado.");
        
        Prestamo pActivo=null;
        for( Prestamo p: prestamos){
            if(p.getMaterial().getCodigo().equalsIgnoreCase(codMaterial)&& !p.isDevuelto()){
                pActivo=p;
                break;
            }
        }
        if (pActivo !=null){
            pActivo.registrarDevolucion(fechaDevolucion);
            Usuario u= pActivo.getUsuario();
            u.getMaterialesPrestado().remove(mat);
            
            if(pActivo.estaVencido(fechaDevolucion)){
                long retraso= pActivo.CalcularDiasRetraso(fechaDevolucion);
                u.aplicarPenalizacion((int)(retraso*2), fechaDevolucion);
            }
        }
        
        mat.devolver();
    }
    
    public void reservarMaterial (String codMaterial, String idUsuario) throws BibliotecaException{
        MaterialBibliografico mat= buscarPorCodigo(codMaterial);
        Usuario usu=buscarUsuarioPorId(idUsuario);
        if (mat == null || usu == null) throw new BibliotecaException("Datos inválidos.");
        if (mat.isDisponible()) throw new BibliotecaException("El material está disponible, tómelo en préstamo directo.");
        mat.reservar(usu.getId());
    }
    //Busqueda Recursiva #1
    public MaterialBibliografico buscarExactoRecursivo(List<MaterialBibliografico> lista, String criterio, int index) {
        if (lista == null || index >= lista.size()) return null;
        MaterialBibliografico m = lista.get(index);
        if (m.getCodigo().equalsIgnoreCase(criterio) || m.getTitulo().equalsIgnoreCase(criterio)) {
            return m;
        }
        return buscarExactoRecursivo(lista, criterio, index + 1);
    }
    //Busqueda recursiva #2
    public void buscarFlexibleRecursivo(List<MaterialBibliografico> lista, Predicate<MaterialBibliografico> filtro, int index, List<MaterialBibliografico> acumulador) {
        if (lista == null || index >= lista.size()) return;
        MaterialBibliografico m = lista.get(index);
        if (filtro.test(m)) {
            acumulador.add(m);
        }
        buscarFlexibleRecursivo(lista, filtro, index + 1, acumulador);
    }
    //recurisividad #3
    public int calcularPenalizacionRecursiva(List<Prestamo> lista, int index, Date fechaRef) {
        if (lista == null || index >= lista.size()) return 0;
        Prestamo p = lista.get(index);
        int dias = (int)(p.CalcularDiasRetraso(fechaRef) * 2);
        return dias + calcularPenalizacionRecursiva(lista, index + 1, fechaRef);
    }
    
    public <T extends MaterialBibliografico> List<T> filtrarPorTipo(Class<T> tipo) {
        List<T> res = new ArrayList<>();
        for (MaterialBibliografico m : catalogo) {
            if (tipo.isInstance(m)) {
                res.add(tipo.cast(m));
            }
        }
        return res;
    }
    
    public List<Prestamo> getPrestamosProximosAVencer(Date fechaRef, int diasMargen) {
        List<Prestamo> res = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fechaRef);
        cal.add(Calendar.DAY_OF_MONTH, diasMargen);
        Date fechaLimite = cal.getTime();

        for (Prestamo p : prestamos) {
            if (!p.isDevuelto()) {
                if (!p.getFechaPrevistaDev().before(fechaRef) && !p.getFechaPrevistaDev().after(fechaLimite)) {
                    res.add(p);
                }
            }
        }
        res.sort(Comparator.comparing(Prestamo::getFechaPrevistaDev));
        return res;
    }
    
    public List<Prestamo> getPrestamosVencidosPendientes(Date fechaRef) {
        List<Prestamo> res = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (!p.isDevuelto() && p.getFechaPrevistaDev().before(fechaRef)) {
                res.add(p);
            }
        }
        res.sort(Comparator.comparing(Prestamo::getFechaPrevistaDev));
        return res;
    }
    
    public MaterialBibliografico buscarPorCodigo(String cod) {
        for (MaterialBibliografico m : catalogo) {
            if (m.getCodigo().equalsIgnoreCase(cod)) return m;
        }
        return null;
    }
    
    public Usuario buscarUsuarioPorId(String id) {
        for (Usuario u : usuarios) {
            if (u.getId().equalsIgnoreCase(id)) return u;
        }
        return null;
    }

    public List<MaterialBibliografico> getCatalogo() {
        return catalogo;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }
    
    private void cargarDatosPrueba(){
        usuarios.add(new UsuarioEstandar("U1", "Carlos Carlos"));
        usuarios.add(new UsuarioPremium("U2", "Ian Suazo"));
        usuarios.add(new UsuarioEstandar("U3", "David Suazo"));
        usuarios.add(new UsuarioPremium( "U4", "Fernando Altamirano"));
        
        // === 2. LIBROS ===
        catalogo.add(new Libro("La Divina Comedia", "LIB-001", NivelComplejidad.ALTO, "portadas/divinacomedia.jpg", "Dante Alighieri", 580, "978-8420665481"));
        catalogo.add(new Libro("La Odisea", "LIB-002", NivelComplejidad.MEDIO, "portadas/odisea.jpg", "Homero", 448, "978-8420674209"));
        catalogo.add(new Libro("La Ilíada", "LIB-003", NivelComplejidad.ALTO, "portadas/iliada.jpg", "Homero", 560, "978-8420674193"));
        catalogo.add(new Libro("El Corazón Delator", "LIB-004", NivelComplejidad.BAJO, "portadas/corazondelator.jpg", "Edgar Allan Poe", 64, "978-8415618751"));
        catalogo.add(new Libro("La Caída de la Casa Usher", "LIB-005", NivelComplejidad.BAJO, "portadas/casausher.jpg", "Edgar Allan Poe", 80, "978-8415618768"));
        catalogo.add(new Libro("Harry Potter y la Piedra Filosofal", "LIB-006", NivelComplejidad.BAJO, "portadas/harrypotter.jpg", "J.K. Rowling", 288, "978-8478884452"));

        // === 3. REVISTAS ===
        catalogo.add(new Revista("National Geographic", "REV-001", NivelComplejidad.BAJO, "portadas/natgeo.jpg", 245, Periodicidad.MENSUAL));
        catalogo.add(new Revista("Time", "REV-002", NivelComplejidad.MEDIO, "portadas/time.jpg", 1050, Periodicidad.SEMANAL));
        catalogo.add(new Revista("Cosmopolitan", "REV-003", NivelComplejidad.BAJO, "portadas/cosmopolitan.jpg", 312, Periodicidad.MENSUAL));
        catalogo.add(new Revista("People Magazine", "REV-004", NivelComplejidad.BAJO, "portadas/people.jpg", 520, Periodicidad.SEMANAL));
        catalogo.add(new Revista("The Lancet", "REV-005", NivelComplejidad.ALTO, "portadas/thelancet.jpg", 884, Periodicidad.SEMANAL));

        // === 4. MATERIAL AUDIOVISUAL ===
        catalogo.add(new Audiovisual("Interstellar", "MED-001", NivelComplejidad.ALTO, "portadas/interstellar.jpg", 169, FormatoAudiovisual.BLURAY));
        catalogo.add(new Audiovisual("Spiderman 2", "MED-002", NivelComplejidad.BAJO, "portadas/spiderman2.jpg", 127, FormatoAudiovisual.DVD));
        catalogo.add(new Audiovisual("The Lion King", "MED-003", NivelComplejidad.BAJO, "portadas/lionking.jpg", 88, FormatoAudiovisual.DVD));
        catalogo.add(new Audiovisual("Avatar", "MED-004", NivelComplejidad.MEDIO, "portadas/avatar.jpg", 162, FormatoAudiovisual.BLURAY));
        catalogo.add(new Audiovisual("La La Land", "MED-005", NivelComplejidad.MEDIO, "portadas/lalaland.jpg", 128, FormatoAudiovisual.BLURAY));
    }
}