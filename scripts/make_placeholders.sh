#!/usr/bin/env bash
# Generates the 30 placeholder restaurant photos.
# Re-runnable: overwrites any placeholder that is still an .xml file.
set -euo pipefail
cd "$(dirname "$0")/.."
out=app/src/main/res/drawable

entries=(
  "r01_jay_fai:#4A2C1B"            "r02_thipsamai:#53301C"
  "r03_krua_apsorn:#5A3520"        "r04_err:#4E3524"
  "r05_nusara:#3F2E22"             "r06_rongros:#453026"
  "r07_nai_ek:#5B3A22"             "r08_hua_seng_hong:#63401F"
  "r09_tk_seafood:#6A4522"         "r10_potong:#4C3323"
  "r11_eighty_twenty:#3E2E24"      "r12_charmgang:#5E3524"
  "r13_prachak:#6B3F23"            "r14_le_du:#44301F"
  "r15_sorn:#5A3A28"               "r16_suhring:#3C2E26"
  "r17_somtum_der:#66451F"         "r18_blue_by_ducasse:#33302E"
  "r19_go_ang:#6E4A25"             "r20_jeh_o_chula:#5C3320"
  "r21_polo_fried_chicken:#6A4A20" "r22_gaggan_anand:#4A2E26"
  "r23_paste:#553824"              "r24_rung_rueang:#5F3D25"
  "r25_wattana_panich:#4B3524"     "r26_supanniga:#59402A"
  "r27_appia:#5D3524"              "r28_peppina:#663D25"
  "r29_sushi_masato:#3A302B"       "r30_baan_tepa:#47382A"
)

written=0
skipped=0

for entry in "${entries[@]}"; do
  name="${entry%%:*}"
  tint="${entry##*:}"
  # A real photo already covers this day. Writing the placeholder anyway would
  # leave two configurations of one resource name and the wrong one could win.
  if compgen -G "app/src/main/res/drawable-nodpi/$name.*" > /dev/null; then
    skipped=$((skipped + 1))
    continue
  fi
  written=$((written + 1))
  cat > "$out/$name.xml" <<XML
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="360dp"
    android:height="200dp"
    android:viewportWidth="360"
    android:viewportHeight="200">
    <path
        android:fillColor="$tint"
        android:pathData="M0,0h360v200h-360z" />
    <path
        android:fillColor="#EFA53D"
        android:pathData="M180,100m-44,0a44,44 0 1,0 88,0a44,44 0 1,0 -88,0" />
    <path
        android:fillColor="$tint"
        android:pathData="M180,100m-32,0a32,32 0 1,0 64,0a32,32 0 1,0 -64,0" />
    <path
        android:fillColor="#EFA53D"
        android:pathData="M118,68h7v64h-7z" />
    <path
        android:fillColor="#EFA53D"
        android:pathData="M235,68h7v64h-7z" />
</vector>
XML
done

echo "wrote $written placeholder drawables to $out"
if [ "$skipped" -gt 0 ]; then
  echo "skipped $skipped day(s) that already have a real photo in drawable-nodpi"
fi
