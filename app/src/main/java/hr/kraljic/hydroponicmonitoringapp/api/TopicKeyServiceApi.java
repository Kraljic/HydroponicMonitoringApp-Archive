package hr.kraljic.hydroponicmonitoringapp.api;

import java.util.List;

import hr.kraljic.hydroponicmonitoringapp.models.TopicKeyDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopicKeyServiceApi {

    @GET("notification/api/topic")
    Call<List<TopicKeyDto>> getTopics(
            @Query("token") String token
    );
}
