# kotlinx.serialization
-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keep,includedescriptorclasses class dev.orionlabs.oriontv.**$$serializer { *; }
-keepclassmembers class dev.orionlabs.oriontv.** {
    *** Companion;
}
-keepclasseswithmembers class com.conreo.couchytv.** {
    kotlinx.serialization.KSerializer serializer(...);
}
