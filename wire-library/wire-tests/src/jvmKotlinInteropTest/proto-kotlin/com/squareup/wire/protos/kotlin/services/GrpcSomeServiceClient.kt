// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: service_kotlin.proto
package com.squareup.wire.protos.kotlin.services

import com.squareup.wire.GrpcCall
import com.squareup.wire.GrpcClient
import com.squareup.wire.GrpcMethod

class GrpcSomeServiceClient(
  private val client: GrpcClient
) : SomeServiceClient {
  override fun SomeMethod(): GrpcCall<SomeRequest, SomeResponse> = client.newCall(GrpcMethod(
      path = "/squareup.protos.kotlin.SomeService/SomeMethod",
      requestAdapter = SomeRequest.ADAPTER,
      responseAdapter = SomeResponse.ADAPTER
  ))
}
