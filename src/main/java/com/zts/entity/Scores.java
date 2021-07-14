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
    public class Scores implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 学号
     */
        @TableId(value = "student_id", type = IdType.AUTO)
      private Integer studentId;

    private String math;

    private String pe;

    private String eng;


}
