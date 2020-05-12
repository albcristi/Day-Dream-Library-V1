package web.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SaleDto extends BaseDto {
    private Long bookid;
    private Long clientid;
    private String clientName;
    private String bookName;
    private String authorName;
}
