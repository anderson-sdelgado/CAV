package br.com.usinasantafe.cav.utils

const val natureList = """
    [
        {
            "id": 1,
            "desc": "ACIDENTE"
        },
        {
            "id": 2,
            "desc": "PANE"
        },
        {
            "id": 3,
            "desc": "AUX. SINAL."
        },
        {
            "id": 4,
            "desc": "AUX. OBRAS"
        },
        {
            "id": 5,
            "desc": "ANIMAIS"
        }
    ]
"""

const val typeAccidentList = """
    [
        {
            "id": 1,
            "desc": "01 - ATROP. PEDESTRE"
        },
        {
            "id": 2,
            "desc": "02 - ATROP. ANIMAL"
        },
        {
            "id": 3,
            "desc": "03 - ATROP. CICLISTA"
        },
        {
            "id": 4,
            "desc": "04 - COLISÃO TRASEIRA"
        },
        {
            "id": 5,
            "desc": "05 - COLISÃO LATERAL"
        },
        {
            "id": 6,
            "desc": "06 - CHOQUE C/ BARRANCO"
        },
        {
            "id": 7,
            "desc": "07 - CHOQUE C/ DEFENSA"
        },
        {
            "id": 8,
            "desc": "08 - CHOQUE C/ ÁRVORE"
        },
        {
            "id": 9,
            "desc": "09 - TOMBAMENTO"
        },
        {
            "id": 10,
            "desc": "10 - CAPOTAMENTO"
        },
        {
            "id": 11,
            "desc": "11 - ABALROAMENTO"
        },
        {
            "id": 12,
            "desc": "12 - ENGAVETAMENTO"
        },
        {
            "id": 13,
            "desc": "13 - INCÊNDIO"
        },
        {
            "id": 14,
            "desc": "14 - QUEDA MOTOCICLISTA"
        },
        {
            "id": 15,
            "desc": "15 - PRECIPITAÇÃO"
        },
        {
            "id": 16,
            "desc": "16 - DANOS MATERIAIS"
        }
    ]
"""

const val optionDataLocalList = """
    [
        {
            "id": 1,
            "desc": "TRAÇADO"
        },
        {
            "id": 2,
            "desc": "PERFIL"
        },
        {
            "id": 3,
            "desc": "LOMBADA"
        },
        {
            "id": 4,
            "desc": "CONSERVAÇÃO"
        },
        {
            "id": 5,
            "desc": "SUPERFÍCIE"
        },
        {
            "id": 6,
            "desc": "AMBIENTE"
        },
        {
            "id": 7,
            "desc": "CANTEIRO CENTRAL"
        },
        {
            "id": 8,
            "desc": "ACOSTAMENTO"
        },
        {
            "id": 9,
            "desc": "OBRAS NA PISTA"
        },
        {
            "id": 10,
            "desc": "OBRAS NO ACOSTAMENTO"
        },
        {
            "id": 11,
            "desc": "SINALIZAÇÃO VERTICAL"
        },
        {
            "id": 12,
            "desc": "SINALIZAÇÃO HORIZONTAL"
        },
        {
            "id": 13,
            "desc": "CONDIÇÕES DO TEMPO"
        },
        {
            "id": 14,
            "desc": "PNEUS DO VEÍCULO"
        },
        {
            "id": 15,
            "desc": "CONGESTIONAMENTO"
        },
        {
            "id": 16,
            "desc": "INTERDIÇÃO DO TRÁFEGO"
        }
    ]
"""

const val itemDataLocalList = """
    [
        {
            "id": 1,
            "desc": "RETA"
        },
        {
            "id": 2,
            "desc": "CURVA ACENTUADA"
        },
        {
            "id": 3,
            "desc": "CURVA SUAVE"
        },
        {
            "id": 4,
            "desc": "À ESQUERDA"
        },
        {
            "id": 5,
            "desc": "À DIREITA"
        },
        {
            "id": 6,
            "desc": "EM NÍVEL"
        },
        {
            "id": 7,
            "desc": "ACLIVE"
        },
        {
            "id": 8,
            "desc": "DECLIVE"
        },
        {
            "id": 9,
            "desc": "ACENTUADO"
        },
        {
            "id": 10,
            "desc": "SUAVE"
        },
        {
            "id": 11,
            "desc": "SIM"
        },
        {
            "id": 12,
            "desc": "NÃO"
        },
        {
            "id": 13,
            "desc": "BOM"
        },
        {
            "id": 14,
            "desc": "REGULAR"
        },
        {
            "id": 15,
            "desc": "RUIM"
        },
        {
            "id": 16,
            "desc": "SECA"
        },
        {
            "id": 17,
            "desc": "ÚMIDA"
        },
        {
            "id": 18,
            "desc": "MOLHADA"
        },
        {
            "id": 19,
            "desc": "POÇA ÁGUA"
        },
        {
            "id": 20,
            "desc": "ÓLEO"
        },
        {
            "id": 21,
            "desc": "FUMAÇA"
        },
        {
            "id": 22,
            "desc": "POEIRA"
        },
        {
            "id": 23,
            "desc": "LAMA"
        },
        {
            "id": 24,
            "desc": "SUJEIRA"
        },
        {
            "id": 25,
            "desc": "OBSTÁCULO"
        },
        {
            "id": 26,
            "desc": "BARREIRA"
        },
        {
            "id": 27,
            "desc": "DEFENSA"
        },
        {
            "id": 28,
            "desc": "MEIO-FIO"
        },
        {
            "id": 29,
            "desc": "CANALETA"
        },
        {
            "id": 30,
            "desc": "NÃO EXISTE"
        },
        {
            "id": 31,
            "desc": "MAL SINALIZADA"
        },
        {
            "id": 32,
            "desc": "BEM SINALIZADA"
        },
        {
            "id": 33,
            "desc": "PROTEÇÃO"
        },
        {
            "id": 34,
            "desc": "EXISTE"
        },
        {
            "id": 35,
            "desc": "MAL CONSERVADA"
        },
        {
            "id": 36,
            "desc": "NORMAL"
        },
        {
            "id": 37,
            "desc": "CHUVA"
        },
        {
            "id": 38,
            "desc": "GAROA"
        },
        {
            "id": 39,
            "desc": "NEBLINA"
        },
        {
            "id": 40,
            "desc": "OUTRAS"
        },
        {
            "id": 41,
            "desc": "QUAL VEÍCULO"
        },
        {
            "id": 42,
            "desc": "MENOS DE 2 KM"
        },
        {
            "id": 43,
            "desc": "MAIS DE 2 KM"
        },
        {
            "id": 44,
            "desc": "PARCIAL"
        },
        {
            "id": 45,
            "desc": "TOTAL"
        }
    ]
"""

const val rOptionItemDataLocalList = """
    [
        {
            "id": 1,
            "idOption": 1,
            "idItem": 1
        },
        {
            "id": 2,
            "idOption": 1,
            "idItem": 2
        },
        {
            "id": 3,
            "idOption": 1,
            "idItem": 3
        },
        {
            "id": 4,
            "idOption": 1,
            "idItem": 4
        },
        {
            "id": 5,
            "idOption": 1,
            "idItem": 5
        },
        {
            "id": 6,
            "idOption": 2,
            "idItem": 6
        },
        {
            "id": 7,
            "idOption": 2,
            "idItem": 7
        },
        {
            "id": 8,
            "idOption": 2,
            "idItem": 8
        },
        {
            "id": 9,
            "idOption": 2,
            "idItem": 9
        },
        {
            "id": 10,
            "idOption": 2,
            "idItem": 10
        },
        {
            "id": 11,
            "idOption": 3,
            "idItem": 11
        },
        {
            "id": 12,
            "idOption": 3,
            "idItem": 12
        },
        {
            "id": 13,
            "idOption": 4,
            "idItem": 13
        },
        {
            "id": 14,
            "idOption": 4,
            "idItem": 14
        },
        {
            "id": 15,
            "idOption": 4,
            "idItem": 15
        },
        {
            "id": 16,
            "idOption": 5,
            "idItem": 16
        },
        {
            "id": 17,
            "idOption": 5,
            "idItem": 17
        },
        {
            "id": 18,
            "idOption": 5,
            "idItem": 18
        },
        {
            "id": 19,
            "idOption": 5,
            "idItem": 19
        },
        {
            "id": 20,
            "idOption": 5,
            "idItem": 20
        },
        {
            "id": 21,
            "idOption": 6,
            "idItem": 21
        },
        {
            "id": 22,
            "idOption": 6,
            "idItem": 22
        },
        {
            "id": 23,
            "idOption": 6,
            "idItem": 23
        },
        {
            "id": 24,
            "idOption": 6,
            "idItem": 24
        },
        {
            "id": 25,
            "idOption": 7,
            "idItem": 25
        },
        {
            "id": 26,
            "idOption": 7,
            "idItem": 26
        },
        {
            "id": 27,
            "idOption": 7,
            "idItem": 27
        },
        {
            "id": 28,
            "idOption": 7,
            "idItem": 28
        },
        {
            "id": 29,
            "idOption": 7,
            "idItem": 29
        },
        {
            "id": 30,
            "idOption": 8,
            "idItem": 25
        },
        {
            "id": 31,
            "idOption": 8,
            "idItem": 26
        },
        {
            "id": 32,
            "idOption": 8,
            "idItem": 27
        },
        {
            "id": 33,
            "idOption": 8,
            "idItem": 28
        },
        {
            "id": 34,
            "idOption": 8,
            "idItem": 29
        },
        {
            "id": 35,
            "idOption": 9,
            "idItem": 30
        },
        {
            "id": 36,
            "idOption": 9,
            "idItem": 31
        },
        {
            "id": 37,
            "idOption": 9,
            "idItem": 32
        },
        {
            "id": 38,
            "idOption": 9,
            "idItem": 33
        },
        {
            "id": 39,
            "idOption": 10,
            "idItem": 30
        },
        {
            "id": 40,
            "idOption": 10,
            "idItem": 31
        },
        {
            "id": 41,
            "idOption": 10,
            "idItem": 32
        },
        {
            "id": 41,
            "idOption": 10,
            "idItem": 33
        },
        {
            "id": 42,
            "idOption": 11,
            "idItem": 34
        },
        {
            "id": 43,
            "idOption": 11,
            "idItem": 30
        },
        {
            "id": 44,
            "idOption": 11,
            "idItem": 32
        },
        {
            "id": 45,
            "idOption": 11,
            "idItem": 35
        },
        {
            "id": 46,
            "idOption": 12,
            "idItem": 34
        },
        {
            "id": 47,
            "idOption": 12,
            "idItem": 30
        },
        {
            "id": 48,
            "idOption": 12,
            "idItem": 32
        },
        {
            "id": 49,
            "idOption": 12,
            "idItem": 35
        },
        {
            "id": 50,
            "idOption": 13,
            "idItem": 36
        },
        {
            "id": 51,
            "idOption": 13,
            "idItem": 37
        },
        {
            "id": 52,
            "idOption": 13,
            "idItem": 38
        },
        {
            "id": 53,
            "idOption": 13,
            "idItem": 39
        },
        {
            "id": 54,
            "idOption": 13,
            "idItem": 40
        },
        {
            "id": 55,
            "idOption": 14,
            "idItem": 13
        },
        {
            "id": 56,
            "idOption": 14,
            "idItem": 14
        },
        {
            "id": 57,
            "idOption": 14,
            "idItem": 14
        },
        {
            "id": 58,
            "idOption": 14,
            "idItem": 15
        },
        {
            "id": 59,
            "idOption": 14,
            "idItem": 41
        },
        {
            "id": 60,
            "idOption": 15,
            "idItem": 11
        },
        {
            "id": 61,
            "idOption": 15,
            "idItem": 12
        },
        {
            "id": 62,
            "idOption": 15,
            "idItem": 42
        },
        {
            "id": 63,
            "idOption": 15,
            "idItem": 43
        },
        {
            "id": 64,
            "idOption": 16,
            "idItem": 11
        },
        {
            "id": 65,
            "idOption": 16,
            "idItem": 12
        },
        {
            "id": 66,
            "idOption": 16,
            "idItem": 44
        },
        {
            "id": 67,
            "idOption": 16,
            "idItem": 45
        }
    ]
"""

const val supportTeamsList = """
    [
        {
            "id": 1,
            "desc": "1 - GUINCHOS"
        },
        {
            "id": 2,
            "desc": "2 - SOS - AMBULÂNCIA"
        },
        {
            "id": 3,
            "desc": "3 - SOS - RESGATE"
        },
        {
            "id": 4,
            "desc": "4 - BOMBEIROS"
        },
        {
            "id": 5,
            "desc": "5 - APOIO AMBIENTAL"
        },
        {
            "id": 6,
            "desc": "6 - PMRVEST"
        },
        {
            "id": 7,
            "desc": "7 - OUTROS"
        }
    ]
"""