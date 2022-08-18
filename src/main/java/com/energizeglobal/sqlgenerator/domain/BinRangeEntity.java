package com.energizeglobal.sqlgenerator.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.List;

@Entity

@Table(name = "BinRange")
public class BinRangeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "BinRange_SubIssuer", joinColumns = @JoinColumn(name = "id_binRange", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_subIssuer", referencedColumnName = "id"))
    private List<SubIssuer> subIssuers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_cryptoConfig", referencedColumnName = "id")
    @JsonIgnore
    private CryptoConfigurationEntity cryptoConfigurationEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SubIssuer> getSubIssuers() {
        return subIssuers;
    }

    public void setSubIssuers(List<SubIssuer> subIssuers) {
        this.subIssuers = subIssuers;
    }

    public CryptoConfigurationEntity getCryptoConfigurationEntity() {
        return cryptoConfigurationEntity;
    }

    public void setCryptoConfigurationEntity(CryptoConfigurationEntity cryptoConfigurationEntity) {
        this.cryptoConfigurationEntity = cryptoConfigurationEntity;
    }


}
