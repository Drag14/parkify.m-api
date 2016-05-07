package agh.edu.pl.parkify;

import akka.http.javadsl.model.ResponseEntity;

/**
 * Created by twozn on 05.05.2016.
 */
class HttpResponseCallback {
	private ResponseEntity responeEntity;

	void onComplete(ResponseEntity responseEntity) {
		this.responeEntity = responseEntity;
	}

	public ResponseEntity getResponeEntity() {
		return responeEntity;
	}
}
