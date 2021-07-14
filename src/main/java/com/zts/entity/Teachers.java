package com.zts.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


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
    public class Teachers implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "teacher_id", type = IdType.AUTO)
      private Integer teacherId;

    private String teacherName;

    private String teacherSex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String  teacherBirthday;

    private String teacherProf;

    private String teacherDepart;
    private String teacherDepartId;


}
