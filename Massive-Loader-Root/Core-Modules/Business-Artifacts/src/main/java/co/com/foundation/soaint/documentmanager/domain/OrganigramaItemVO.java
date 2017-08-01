package co.com.foundation.soaint.documentmanager.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;

public class OrganigramaItemVO {

    @JsonProperty("id")
    private Long ideOrgaAdmin;
    @JsonProperty("code")
    private String codOrg;
    @JsonProperty("text")
    private String nomOrg;
    private Long idOrgaAdminPadre;
    @JsonProperty("parent")
    private String refPadre;
    @JsonProperty("textParent")
    private String nomPadre;
    @JsonProperty("status")
    private String estado;
    @JsonProperty("level")
    private Integer nivel;
    @JsonProperty("desc")
    private String descOrg;
    @JsonProperty("levelParent")
    private Integer nivelPadre;


    public OrganigramaItemVO(Long ideOrgaAdmin, String codOrg, String nomOrg, String estado ) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.estado = estado;
        refPadre = "#";
    }

    public OrganigramaItemVO(Long ideOrgaAdmin, String codOrg, String nomOrg, String estado, Integer nivel ) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.estado = estado;
        refPadre = "#";
        this.nivel =nivel;
    }

    public OrganigramaItemVO(Long ideOrgaAdmin, String codOrg, String nomOrg, String estado, String descOrg, Integer nivel) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.estado = estado;
        this.descOrg = descOrg;
        this.nivel = nivel;
        refPadre = "#";

    }

    public OrganigramaItemVO(Long ideOrgaAdmin, String codOrg, String nomOrg, Long idOrgaAdminPadre, String estado, String descOrg, Integer nivel) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.estado = estado;
        this.descOrg = descOrg;
        this.nivel = nivel;
        refPadre = "#";
        this.ideOrgaAdmin = idOrgaAdminPadre;

    }

    public OrganigramaItemVO(Long ideOrgaAdmin, String codOrg, String nomOrg, Long idOrgaAdminPadre, String nomPadre,
                             String estado, Integer nivel, String descOrg, Integer nivelPadre) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        this.codOrg = codOrg;
        this.nomOrg = nomOrg;
        this.idOrgaAdminPadre = idOrgaAdminPadre;
        refPadre = getIdOrgaAdminPadre().toString();
        this.nomPadre = nomPadre;
        this.estado = estado;
        this.nivel = nivel;
        this.descOrg = descOrg;
        this.nivelPadre = nivelPadre;
    }

    public Long getIdeOrgaAdmin() {
        return ideOrgaAdmin;
    }

    public String getCodOrg() {
        return codOrg;
    }

    public String getNomOrg() {
        return nomOrg;
    }

    public Long getIdOrgaAdminPadre() {
        return idOrgaAdminPadre;
    }

    public String getRefPadre() {
        return refPadre;
    }

    public String getNomPadre() {
        return nomPadre;
    }

    public String getEstado() {
        return estado;
    }

    public Integer getNivel() {
        return nivel;
    }

    public String getDescOrg() {
        return descOrg;
    }

    public Integer getNivelPadre() {
        return nivelPadre;
    }


    public void setIdeOrgaAdmin(Long ideOrgaAdmin) {
        this.ideOrgaAdmin = ideOrgaAdmin;
    }

    public void setCodOrg(String codOrg) {
        this.codOrg = codOrg;
    }

    public void setNomOrg(String nomOrg) {
        this.nomOrg = nomOrg;
    }

    public void setIdOrgaAdminPadre(Long idOrgaAdminPadre) {
        this.idOrgaAdminPadre = idOrgaAdminPadre;
    }

    public void setRefPadre(String refPadre) {
        this.refPadre = refPadre;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setNomPadre(String nomPadre) {
        this.nomPadre = nomPadre;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public void setDescOrg(String descOrg) {
        this.descOrg = descOrg;
    }

    public void setNivelPadre(Integer nivelPadre) {
        this.nivelPadre = nivelPadre;
    }

    @Override
    public String toString() {
        return "OrganigramaItemVO{" +
                "ideOrgaAdmin=" + ideOrgaAdmin +
                ", codOrg='" + codOrg + '\'' +
                ", nomOrg='" + nomOrg + '\'' +
                ", idOrgaAdminPadre=" + idOrgaAdminPadre +
                ", refPadre='" + refPadre + '\'' +
                ", nomPadre='" + nomPadre + '\'' +
                ", estado='" + estado + '\'' +
                ", nivel=" + nivel +
                ", descOrg='" + descOrg + '\'' +
                ", nivelPadre=" + nivelPadre +
                '}';
    }

    // Comparator
    public static class CompNivel implements Comparator<OrganigramaItemVO> {

        @Override
        public int compare(OrganigramaItemVO o1, OrganigramaItemVO o2) {
            return o1.getIdeOrgaAdmin().intValue() - o2.getIdeOrgaAdmin().intValue();
        }
    }
}
