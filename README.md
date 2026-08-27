# Mashwarak Customer

مشروع Android مرتب وجاهز للرفع إلى GitHub.

## بنية المشروع

- `app/src/main/AndroidManifest.xml`
- `app/src/main/java/com/mashwarak/customer/MainActivity.kt`
- `app/src/main/res/values/strings.xml`
- `app/src/main/res/values/themes.xml`
- `app/build.gradle.kts`
- `build.gradle.kts`
- `settings.gradle.kts`
- `.github/workflows/build.yml`

## بناء APK

بعد رفع الملفات إلى GitHub على فرع `main`، افتح تبويب **Actions** وانتظر انتهاء
workflow باسم **Build APK**. سيظهر ملف `MashwarakCustomer-debug` ضمن Artifacts.

هذا الإصدار هو واجهة أولية تعمل محلياً: يعرض موقع الانطلاق والوجهة وزر طلب مشوار.
