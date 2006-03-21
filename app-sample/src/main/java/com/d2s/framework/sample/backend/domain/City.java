/*
 * Generated by Design2see. All rights reserved.
 */
package com.d2s.framework.sample.backend.domain;

/**
 * City entity.
 * <p>
 * Generated by Design2see. All rights reserved.
 * <p>
 * 
 * @hibernate.mapping default-access =
 *                    "com.d2s.framework.model.persistence.hibernate.property.EntityPropertyAccessor"
 *                    package = "com.d2s.framework.sample.backend.domain"
 * @hibernate.class table = "CITY" dynamic-insert = "true" dynamic-update =
 *                  "true" persister =
 *                  "com.d2s.framework.model.persistence.hibernate.entity.persister.EntityProxyJoinedSubclassEntityPersister"
 * @author Generated by Design2see
 * @version $LastChangedRevision$
 */
public interface City extends com.d2s.framework.sample.backend.domain.Nameable,
    com.d2s.framework.model.entity.IEntity,
    com.d2s.framework.sample.backend.domain.Traceable {

  /**
   * @hibernate.id generator-class = "assigned" column = "ID" type = "string"
   *               length = "36"
   *               <p>
   *               {@inheritDoc}
   */
  java.io.Serializable getId();

  /**
   * @hibernate.version column = "VERSION" unsaved-value = "null"
   *                    <p>
   *                    {@inheritDoc}
   */
  Integer getVersion();

  /**
   * Gets the zip.
   * 
   * @hibernate.property
   * @hibernate.column name = "ZIP" length = "5"
   * @return the zip.
   */
  java.lang.String getZip();

  /**
   * Sets the zip.
   * 
   * @param zip
   *          the zip to set.
   */
  void setZip(java.lang.String zip);

  /**
   * Gets the country.
   * 
   * @hibernate.property
   * @hibernate.column name = "COUNTRY"
   * @return the country.
   */
  java.lang.String getCountry();

  /**
   * Sets the country.
   * 
   * @param country
   *          the country to set.
   */
  void setCountry(java.lang.String country);

}
