/*
 * Copyright 2020 Square Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.squareup.wire

import com.squareup.wire.proto2.simple.SimpleMessage
import com.squareup.wire.proto2.simple.SimpleMessageOuterClass
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Proto2WireProtocCompatibilityTests {
  @Test fun simpleMessage() {
    val wireMessage = SimpleMessage(
        optional_nested_msg = SimpleMessage.NestedMessage(806),
        no_default_nested_enum = SimpleMessage.NestedEnum.BAR,
        repeated_double = listOf(1.0, 33.0),
        required_int32 = 46,
        other = "hello"
    )

    val googleMessage = SimpleMessageOuterClass.SimpleMessage.newBuilder()
        .setOptionalNestedMsg(
            SimpleMessageOuterClass.SimpleMessage.NestedMessage.newBuilder().setBb(806).build()
        )
        .setNoDefaultNestedEnum(SimpleMessageOuterClass.SimpleMessage.NestedEnum.BAR)
        .addAllRepeatedDouble(listOf(1.0, 33.0))
        .setRequiredInt32(46)
        .setOther("hello")
        .build()

    val encodedWireMessage: ByteArray = wireMessage.encode()
    val encodedGoogleMessage: ByteArray = googleMessage.toByteArray()

    assertThat(encodedWireMessage).isEqualTo(encodedGoogleMessage)

    val wireMessageDecodedFromGoogleMessage =
        SimpleMessage.ADAPTER.decode(encodedGoogleMessage)
    val googleMessageDecodedFromWireMessage =
        SimpleMessageOuterClass.SimpleMessage.parseFrom(encodedWireMessage)

    assertThat(wireMessageDecodedFromGoogleMessage).isEqualTo(wireMessage)
    assertThat(googleMessageDecodedFromWireMessage).isEqualTo(googleMessage)
  }
}
