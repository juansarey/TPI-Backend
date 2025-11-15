package tpi.transporte.operaciones.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import tpi.transporte.operaciones.dtos.ClienteLiteDTO;
import tpi.transporte.operaciones.dtos.ContenedorLiteDTO;

@Component
public class MaestrosServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${clients.maestros.base-url}")
    private String maestrosBaseUrl;

    /**
     * Valida que un cliente exista en maestros-service
     * @param clienteId ID del cliente a validar
     * @return ClienteLiteDTO si existe, null si no existe
     */
    public ClienteLiteDTO validarYObtenerCliente(Long clienteId) {
        try {
            String url = maestrosBaseUrl + "/clientes/" + clienteId;
            ResponseEntity<ClienteResponseDTO> response = restTemplate.getForEntity(url, ClienteResponseDTO.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ClienteResponseDTO cliente = response.getBody();
                return new ClienteLiteDTO(
                    cliente.getIdCliente(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getTelefono()
                );
            }
            return null;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con maestros-service para validar cliente: " + e.getMessage(), e);
        }
    }

    /**
     * Valida que un contenedor exista en maestros-service
     * @param contenedorId ID del contenedor a validar
     * @return ContenedorLiteDTO si existe, null si no existe
     */
    public ContenedorLiteDTO validarYObtenerContenedor(Long contenedorId) {
        try {
            String url = maestrosBaseUrl + "/contenedores/" + contenedorId;
            ResponseEntity<ContenedorResponseDTO> response = restTemplate.getForEntity(url, ContenedorResponseDTO.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                ContenedorResponseDTO contenedor = response.getBody();
                return new ContenedorLiteDTO(
                    contenedor.getIdContenedor(),
                    contenedor.getPesoKg(),
                    contenedor.getVolumenM3()
                );
            }
            return null;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con maestros-service para validar contenedor: " + e.getMessage(), e);
        }
    }

    /**
     * Valida que una tarifa exista en maestros-service y la obtiene
     * @param tarifaId ID de la tarifa a validar
     * @return TarifaDTO si existe, null si no existe
     */
    public TarifaDTO validarYObtenerTarifa(Long tarifaId) {
        try {
            String url = maestrosBaseUrl + "/tarifas/" + tarifaId;
            ResponseEntity<TarifaDTO> response = restTemplate.getForEntity(url, TarifaDTO.class);
            
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody();
            }
            return null;
        } catch (HttpClientErrorException.NotFound e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con maestros-service para validar tarifa: " + e.getMessage(), e);
        }
    }
    
    /**
     * Clase DTO pública para representar una tarifa desde maestros-service
     */
    public static class TarifaDTO {
        private Long idTarifa;
        private Double costoKmBase;
        private Double costoBaseFijo;

        public Long getIdTarifa() { return idTarifa; }
        public void setIdTarifa(Long idTarifa) { this.idTarifa = idTarifa; }
        public Double getCostoKmBase() { return costoKmBase; }
        public void setCostoKmBase(Double costoKmBase) { this.costoKmBase = costoKmBase; }
        public Double getCostoBaseFijo() { return costoBaseFijo; }
        public void setCostoBaseFijo(Double costoBaseFijo) { this.costoBaseFijo = costoBaseFijo; }
    }

    /**
     * Valida que un camión exista en maestros-service
     * @param camionId ID del camión a validar
     * @return true si existe, false si no existe
     */
    public boolean validarCamion(Long camionId) {
        try {
            String url = maestrosBaseUrl + "/camiones/" + camionId;
            ResponseEntity<CamionDTO> response = restTemplate.getForEntity(url, CamionDTO.class);
            return response.getStatusCode() == HttpStatus.OK && response.getBody() != null;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            throw new RuntimeException("Error al comunicarse con maestros-service para validar camión: " + e.getMessage(), e);
        }
    }

    // DTOs internos para mapear las respuestas de maestros-service
    private static class ClienteResponseDTO {
        private Long idCliente;
        private String nombre;
        private String apellido;
        private String telefono;
        private String direccion;

        public Long getIdCliente() { return idCliente; }
        public void setIdCliente(Long idCliente) { this.idCliente = idCliente; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public String getApellido() { return apellido; }
        public void setApellido(String apellido) { this.apellido = apellido; }
        public String getTelefono() { return telefono; }
        public void setTelefono(String telefono) { this.telefono = telefono; }
        public String getDireccion() { return direccion; }
        public void setDireccion(String direccion) { this.direccion = direccion; }
    }

    private static class ContenedorResponseDTO {
        private Long idContenedor;
        private Double pesoKg;
        private Double volumenM3;

        public Long getIdContenedor() { return idContenedor; }
        public void setIdContenedor(Long idContenedor) { this.idContenedor = idContenedor; }
        public Double getPesoKg() { return pesoKg; }
        public void setPesoKg(Double pesoKg) { this.pesoKg = pesoKg; }
        public Double getVolumenM3() { return volumenM3; }
        public void setVolumenM3(Double volumenM3) { this.volumenM3 = volumenM3; }
    }


    private static class CamionDTO {
        private Long idCamion;

        public Long getIdCamion() { return idCamion; }
        public void setIdCamion(Long idCamion) { this.idCamion = idCamion; }
    }
}

