package uz.pdp.cutecutapp.dto.responce;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.pdp.cutecutapp.dto.BaseDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataDto<T> implements BaseDto {

    protected T data;

    protected AppErrorDto error;

    protected boolean success;

    private Long totalCount;

    private Integer status;

    public DataDto(boolean success) {
        this.success = success;
    }

    public DataDto(T data) {
        this.data = data;
        this.success = true;
    }

    public DataDto(AppErrorDto error) {
        this.error = error;
        this.success = false;
    }

    public DataDto(T data, Long totalCount) {
        this.data = data;
        this.success = true;
        this.totalCount = totalCount;
    }

    public DataDto(T data, Integer status) {
        this.data = data;
        this.success = true;
        this.status = status;
    }
}