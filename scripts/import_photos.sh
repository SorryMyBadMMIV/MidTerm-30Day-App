#!/usr/bin/env bash
# Imports Day1.jpg .. Day30.jpg from a folder into res/drawable-nodpi, renaming
# each to the resource name its day expects, and clears the matching placeholder.
#
#   bash scripts/import_photos.sh "/c/Users/poohk/OneDrive/Desktop/restaurant photo"
#
# drawable-nodpi, not drawable: a bitmap in plain res/drawable is assumed to be
# mdpi and gets upscaled by the device's density factor when decoded, so a 3000px
# photo becomes a 9000px bitmap on an xxhdpi phone and blows the heap. nodpi means
# "use this at its native size".
set -euo pipefail
cd "$(dirname "$0")/.."

src="${1:-/c/Users/poohk/OneDrive/Desktop/restaurant photo}"
out=app/src/main/res/drawable-nodpi
placeholders=app/src/main/res/drawable

names=(
  r01_jay_fai r02_thipsamai r03_krua_apsorn r04_err r05_nusara
  r06_rongros r07_nai_ek r08_hua_seng_hong r09_tk_seafood r10_potong
  r11_eighty_twenty r12_charmgang r13_prachak r14_le_du r15_sorn
  r16_suhring r17_somtum_der r18_blue_by_ducasse r19_go_ang r20_jeh_o_chula
  r21_polo_fried_chicken r22_gaggan_anand r23_paste r24_rung_rueang
  r25_wattana_panich r26_supanniga r27_appia r28_peppina r29_sushi_masato
  r30_baan_tepa
)

if [ ! -d "$src" ]; then
  echo "source folder not found: $src" >&2
  exit 1
fi

mkdir -p "$out"
imported=0
missing=()

for day in $(seq 1 30); do
  photo="$src/Day$day.jpg"
  name="${names[$((day - 1))]}"
  if [ ! -f "$photo" ]; then
    missing+=("Day$day.jpg")
    continue
  fi
  cp "$photo" "$out/$name.jpg"
  # A placeholder left in res/drawable would be a second configuration of the
  # same resource name and could win on some densities.
  rm -f "$placeholders/$name.xml"
  imported=$((imported + 1))
done

echo "imported $imported photos into $out"
if [ ${#missing[@]} -gt 0 ]; then
  echo "MISSING (placeholder kept): ${missing[*]}" >&2
fi
