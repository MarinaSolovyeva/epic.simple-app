package DTO;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * IncrementRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-08-22T19:52:11.125029300+03:00[Europe/Moscow]")


public class IncrementRequest   {
  @JsonProperty("counterId")
  private String counterId = null;

  @JsonProperty("incrementCount")
  private Integer incrementCount = null;

  public IncrementRequest counterId(String counterId) {
    this.counterId = counterId;
    return this;
  }

  /**
   * Get counterId
   * @return counterId
   **/
  @Schema(description = "")
  
    public String getCounterId() {
    return counterId;
  }

  public void setCounterId(String counterId) {
    this.counterId = counterId;
  }

  public IncrementRequest incrementCount(Integer incrementCount) {
    this.incrementCount = incrementCount;
    return this;
  }

  /**
   * Get incrementCount
   * minimum: 1
   * @return incrementCount
   **/
  @Schema(description = "")
  
  @Min(1)  public Integer getIncrementCount() {
    return incrementCount;
  }

  public void setIncrementCount(Integer incrementCount) {
    this.incrementCount = incrementCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IncrementRequest incrementRequest = (IncrementRequest) o;
    return Objects.equals(this.counterId, incrementRequest.counterId) &&
        Objects.equals(this.incrementCount, incrementRequest.incrementCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(counterId, incrementCount);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IncrementRequest {\n");
    
    sb.append("    counterId: ").append(toIndentedString(counterId)).append("\n");
    sb.append("    incrementCount: ").append(toIndentedString(incrementCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
