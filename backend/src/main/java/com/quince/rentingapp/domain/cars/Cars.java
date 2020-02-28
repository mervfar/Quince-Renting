package com.quince.rentingapp.domain.cars;

import com.quince.rentingapp.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "cars")
public class Cars extends BaseEntity {

    private String name;
}
