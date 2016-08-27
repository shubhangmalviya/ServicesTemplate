package com.daffodilsw.servicestemplate.post.raw;

import retrofit2.Call;

public interface RawService {

    <T> Call<T> makePostRequest();

}
