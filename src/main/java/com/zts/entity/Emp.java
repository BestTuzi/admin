package com.zts.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Tuzi
 * @since 2021-07-11
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Emp implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "emp_id", type = IdType.AUTO)
      private Integer empId;

    private String empName;

    private String empSex;

    private String empAddress;

    private String empProf;


}
