package by.itacademy.entity.productEntity;

import by.itacademy.entity.otherEntity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "details")
@Data
@EqualsAndHashCode(callSuper = true)
public class Detail extends BaseEntity {
    @Column(name = "name")
    private String name;
}
