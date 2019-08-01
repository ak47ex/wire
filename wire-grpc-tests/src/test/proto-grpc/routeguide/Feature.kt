// Code generated by Wire protocol buffer compiler, do not edit.
// Source file: routeguide/RouteGuideProto.proto
package routeguide

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.WireField
import kotlin.Any
import kotlin.AssertionError
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.Int
import kotlin.Nothing
import kotlin.String
import kotlin.hashCode
import kotlin.jvm.JvmField
import okio.ByteString

/**
 * A feature names something at a given point.
 *
 * If a feature could not be named, the name is empty.
 */
class Feature(
  /**
   * The name of the feature.
   */
  @field:WireField(
    tag = 1,
    adapter = "com.squareup.wire.ProtoAdapter#STRING"
  )
  val name: String? = null,
  /**
   * The point where the feature is detected.
   */
  @field:WireField(
    tag = 2,
    adapter = "routeguide.Point#ADAPTER"
  )
  val location: Point? = null,
  unknownFields: ByteString = ByteString.EMPTY
) : Message<Feature, Nothing>(ADAPTER, unknownFields) {
  @Deprecated(
    message = "Shouldn't be used in Kotlin",
    level = DeprecationLevel.HIDDEN
  )
  override fun newBuilder(): Nothing {
    throw AssertionError()
  }

  override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is Feature) return false
    return unknownFields == other.unknownFields
        && name == other.name
        && location == other.location
  }

  override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = result * 37 + name.hashCode()
      result = result * 37 + location.hashCode()
      super.hashCode = result
    }
    return result
  }

  override fun toString(): String {
    val result = mutableListOf<String>()
    if (name != null) result += """name=$name"""
    if (location != null) result += """location=$location"""
    return result.joinToString(prefix = "Feature{", separator = ", ", postfix = "}")
  }

  fun copy(
    name: String? = this.name,
    location: Point? = this.location,
    unknownFields: ByteString = this.unknownFields
  ): Feature = Feature(name, location, unknownFields)

  companion object {
    @JvmField
    val ADAPTER: ProtoAdapter<Feature> = object : ProtoAdapter<Feature>(
      FieldEncoding.LENGTH_DELIMITED, 
      Feature::class
    ) {
      override fun encodedSize(value: Feature): Int = 
        ProtoAdapter.STRING.encodedSizeWithTag(1, value.name) +
        Point.ADAPTER.encodedSizeWithTag(2, value.location) +
        value.unknownFields.size

      override fun encode(writer: ProtoWriter, value: Feature) {
        ProtoAdapter.STRING.encodeWithTag(writer, 1, value.name)
        Point.ADAPTER.encodeWithTag(writer, 2, value.location)
        writer.writeBytes(value.unknownFields)
      }

      override fun decode(reader: ProtoReader): Feature {
        var name: String? = null
        var location: Point? = null
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> name = ProtoAdapter.STRING.decode(reader)
            2 -> location = Point.ADAPTER.decode(reader)
            else -> reader.readUnknownField(tag)
          }
        }
        return Feature(
          name = name,
          location = location,
          unknownFields = unknownFields
        )
      }

      override fun redact(value: Feature): Feature = value.copy(
        location = value.location?.let(Point.ADAPTER::redact),
        unknownFields = ByteString.EMPTY
      )
    }
  }
}
