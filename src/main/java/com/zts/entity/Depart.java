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
 * @since 2021-07-08
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class Depart implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "depart_id", type = IdType.AUTO)
      private Integer departId;

    private String departName;

    private String departAddress;


}
