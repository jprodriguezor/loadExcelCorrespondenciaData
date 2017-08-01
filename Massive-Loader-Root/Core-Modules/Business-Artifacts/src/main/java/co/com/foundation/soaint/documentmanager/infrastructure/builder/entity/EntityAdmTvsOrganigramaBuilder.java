package co.com.foundation.soaint.documentmanager.infrastructure.builder.entity;

import co.com.foundation.soaint.documentmanager.persistence.entity.TvsConfigOrgAdministrativo;

import java.math.BigInteger;
import java.util.Date;

/**
 * Created by jrodriguez on 30/10/2016.
 */
public class EntityAdmTvsOrganigramaBuilder {


    private Long ideOrgaAdmin;
    private String codOrg;
    private String nomOrg;
    private String descOrg;
    private String indEsActivo;
    private BigInteger ideDireccion;
    private BigInteger idePlanResponsable;
    private Integer codNivel;
    private Date fecCreacion;
    private Long ideUsuarioCreo;
    private long ideUsuarioCambio;
    private Date fecCambio;
    private int nivLectura;
    private int nivEscritura;
    private String ideUuid;
    private String valSistema;
    private TvsConfigOrgAdministrativo ideOrgaAdminPadre;

    private EntityAdmTvsOrganigramaBuilder() {
    }

    public static EntityAdmTvsOrganigramaBuilder newBuilder() {return new EntityAdmTvsOrganigramaBuilder();}

    public EntityAdmTvsOrganigramaBuilder withCodOrg(final String codOrg) {
        this.codOrg = codOrg;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withNomOrg(final String nomOrg) {
        this.nomOrg = nomOrg;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIdeOrgaAdmin(final Long ideOrgaAdmin) {
        this.ideOrgaAdmin = ideOrgaAdmin;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withDescOrg(final String descOrg) {
        this.descOrg = descOrg;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIndEsActivo(final String indEsActivo) {
        this.indEsActivo = indEsActivo;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIdeDireccion(final BigInteger ideDireccion) {
        this.ideDireccion = ideDireccion;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIdePlanResponsable(final BigInteger idePlanResponsable) {
        this.idePlanResponsable = idePlanResponsable;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withCodNivel(final Integer codNivel) {
        this.codNivel = codNivel;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withFecCreacion(final Date fecCreacion) {
        this.fecCreacion = fecCreacion;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIdeUsuarioCreo(final Long ideUsuarioCreo) {
        this.ideUsuarioCreo = ideUsuarioCreo;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIdeUsuarioCambio(final long ideUsuarioCambio) {
        this.ideUsuarioCambio = ideUsuarioCambio;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withFecCambio(final Date fecCambio) {
        this.fecCambio = fecCambio;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withNivLectura(final int nivLectura) {
        this.nivLectura = nivLectura;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withNivEscritura(final int nivEscritura) {
        this.nivEscritura = nivEscritura;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIdeUuid(final String ideUuid) {
        this.ideUuid = ideUuid;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withValSistema(final String valSistema) {
        this.valSistema = valSistema;
        return this;
    }

    public EntityAdmTvsOrganigramaBuilder withIdeOrgaAdminPadre(final TvsConfigOrgAdministrativo ideOrgaAdminPadre) {
        this.ideOrgaAdminPadre = ideOrgaAdminPadre;
        return this;
    }

    public TvsConfigOrgAdministrativo build() {
        return new TvsConfigOrgAdministrativo(ideOrgaAdmin, codOrg, nomOrg, indEsActivo, descOrg, codNivel, fecCreacion,
                fecCambio, valSistema, ideOrgaAdminPadre);
    }


}
