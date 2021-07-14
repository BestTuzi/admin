package com.zts.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
    public class Students implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "student_id", type = IdType.AUTO)
      private Integer studentId;

    private String studentName;

    private String studentSex;

    private String studentBirthday;

    private String studentAddress;

    @TableField(exist = false)
    private Scores scores;


}
