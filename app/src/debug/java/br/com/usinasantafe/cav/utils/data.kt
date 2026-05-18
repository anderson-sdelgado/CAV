package br.com.usinasantafe.cav.utils

const val natureList = """
    [
        {
            "id": 1,
            "description": "ACIDENTE"
        },
        {
            "id": 2,
            "description": "PANE"
        },
        {
            "id": 3,
            "description": "AUX. SINAL."
        },
        {
            "id": 4,
            "description": "AUX. OBRAS"
        },
        {
            "id": 5,
            "description": "ANIMAIS"
        }
    ]
"""

const val typeAccidentList = """
    [
        {
            "id": 1,
            "description": "ATROP. PEDESTRE"
        },
        {
            "id": 2,
            "description": "ATROP. ANIMAL"
        },
        {
            "id": 3,
            "description": "ATROP. CICLISTA"
        },
        {
            "id": 4,
            "description": "COLISÃO TRASEIRA"
        },
        {
            "id": 5,
            "description": "COLISÃO LATERAL"
        },
        {
            "id": 6,
            "description": "CHOQUE C/ BARRANCO"
        },
        {
            "id": 7,
            "description": "CHOQUE C/ DEFENSA"
        },
        {
            "id": 8,
            "description": "CHOQUE C/ ÁRVORE"
        },
        {
            "id": 9,
            "description": "TOMBAMENTO"
        },
        {
            "id": 10,
            "description": "CAPOTAMENTO"
        },
        {
            "id": 11,
            "description": "ABALROAMENTO"
        },
        {
            "id": 12,
            "description": "ENGAVETAMENTO"
        },
        {
            "id": 13,
            "description": "INCÊNDIO"
        },
        {
            "id": 14,
            "description": "QUEDA MOTOCICLISTA"
        },
        {
            "id": 15,
            "description": "PRECIPITAÇÃO"
        },
        {
            "id": 16,
            "description": "DANOS MATERIAIS"
        }
    ]
"""

const val optionDataLocalList = """
    [
        {
            "id": 1,
            "description": "TRAÇADO"
        },
        {
            "id": 2,
            "description": "PERFIL"
        },
        {
            "id": 3,
            "description": "LOMBADA"
        },
        {
            "id": 4,
            "description": "CONSERVAÇÃO"
        },
        {
            "id": 5,
            "description": "SUPERFÍCIE"
        },
        {
            "id": 6,
            "description": "AMBIENTE"
        },
        {
            "id": 7,
            "description": "CANTEIRO CENTRAL"
        },
        {
            "id": 8,
            "description": "ACOSTAMENTO"
        },
        {
            "id": 9,
            "description": "OBRAS NA PISTA"
        },
        {
            "id": 10,
            "description": "OBRAS NO ACOSTAMENTO"
        },
        {
            "id": 11,
            "description": "SINALIZAÇÃO VERTICAL"
        },
        {
            "id": 12,
            "description": "SINALIZAÇÃO HORIZONTAL"
        },
        {
            "id": 13,
            "description": "CONDIÇÕES DO TEMPO"
        },
        {
            "id": 14,
            "description": "PNEUS DO VEÍCULO"
        },
        {
            "id": 15,
            "description": "CONGESTIONAMENTO"
        },
        {
            "id": 16,
            "description": "INTERDIÇÃO DO TRÁFEGO"
        }
    ]
"""

const val itemDataLocalList = """
    [
        {
            "id": 1,
            "description": "RETA"
        },
        {
            "id": 2,
            "description": "CURVA ACENTUADA"
        },
        {
            "id": 3,
            "description": "CURVA SUAVE"
        },
        {
            "id": 4,
            "description": "À ESQUERDA"
        },
        {
            "id": 5,
            "description": "À DIREITA"
        },
        {
            "id": 6,
            "description": "EM NÍVEL"
        },
        {
            "id": 7,
            "description": "ACLIVE"
        },
        {
            "id": 8,
            "description": "DECLIVE"
        },
        {
            "id": 9,
            "description": "ACENTUADO"
        },
        {
            "id": 10,
            "description": "SUAVE"
        },
        {
            "id": 11,
            "description": "SIM"
        },
        {
            "id": 12,
            "description": "NÃO"
        },
        {
            "id": 13,
            "description": "BOM"
        },
        {
            "id": 14,
            "description": "REGULAR"
        },
        {
            "id": 15,
            "description": "RUIM"
        },
        {
            "id": 16,
            "description": "SECA"
        },
        {
            "id": 17,
            "description": "ÚMIDA"
        },
        {
            "id": 18,
            "description": "MOLHADA"
        },
        {
            "id": 19,
            "description": "POÇA ÁGUA"
        },
        {
            "id": 20,
            "description": "ÓLEO"
        },
        {
            "id": 21,
            "description": "FUMAÇA"
        },
        {
            "id": 22,
            "description": "POEIRA"
        },
        {
            "id": 23,
            "description": "LAMA"
        },
        {
            "id": 24,
            "description": "SUJEIRA"
        },
        {
            "id": 25,
            "description": "OBSTÁCULO"
        },
        {
            "id": 26,
            "description": "BARREIRA"
        },
        {
            "id": 27,
            "description": "DEFENSA"
        },
        {
            "id": 28,
            "description": "MEIO-FIO"
        },
        {
            "id": 29,
            "description": "CANALETA"
        },
        {
            "id": 30,
            "description": "NÃO EXISTE"
        },
        {
            "id": 31,
            "description": "MAL SINALIZADA"
        },
        {
            "id": 32,
            "description": "BEM SINALIZADA"
        },
        {
            "id": 33,
            "description": "PROTEÇÃO"
        },
        {
            "id": 34,
            "description": "EXISTE"
        },
        {
            "id": 35,
            "description": "MAL CONSERVADA"
        },
        {
            "id": 36,
            "description": "NORMAL"
        },
        {
            "id": 37,
            "description": "CHUVA"
        },
        {
            "id": 38,
            "description": "GAROA"
        },
        {
            "id": 39,
            "description": "NEBLINA"
        },
        {
            "id": 40,
            "description": "OUTRAS"
        },
        {
            "id": 41,
            "description": "QUAL VEÍCULO"
        },
        {
            "id": 42,
            "description": "MENOS DE 2 KM"
        },
        {
            "id": 43,
            "description": "MAIS DE 2 KM"
        },
        {
            "id": 44,
            "description": "PARCIAL"
        },
        {
            "id": 45,
            "description": "TOTAL"
        }
    ]
"""

const val dataLocalList = """
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
            "id": 42,
            "idOption": 10,
            "idItem": 33
        },
        {
            "id": 43,
            "idOption": 11,
            "idItem": 34
        },
        {
            "id": 44,
            "idOption": 11,
            "idItem": 30
        },
        {
            "id": 45,
            "idOption": 11,
            "idItem": 32
        },
        {
            "id": 46,
            "idOption": 11,
            "idItem": 35
        },
        {
            "id": 47,
            "idOption": 12,
            "idItem": 34
        },
        {
            "id": 48,
            "idOption": 12,
            "idItem": 30
        },
        {
            "id": 49,
            "idOption": 12,
            "idItem": 32
        },
        {
            "id": 50,
            "idOption": 12,
            "idItem": 35
        },
        {
            "id": 51,
            "idOption": 13,
            "idItem": 36
        },
        {
            "id": 52,
            "idOption": 13,
            "idItem": 37
        },
        {
            "id": 53,
            "idOption": 13,
            "idItem": 38
        },
        {
            "id": 54,
            "idOption": 13,
            "idItem": 39
        },
        {
            "id": 55,
            "idOption": 13,
            "idItem": 40
        },
        {
            "id": 56,
            "idOption": 14,
            "idItem": 13
        },
        {
            "id": 57,
            "idOption": 14,
            "idItem": 14
        },
        {
            "id": 58,
            "idOption": 14,
            "idItem": 14
        },
        {
            "id": 59,
            "idOption": 14,
            "idItem": 15
        },
        {
            "id": 60,
            "idOption": 14,
            "idItem": 41
        },
        {
            "id": 61,
            "idOption": 15,
            "idItem": 11
        },
        {
            "id": 62,
            "idOption": 15,
            "idItem": 12
        },
        {
            "id": 63,
            "idOption": 15,
            "idItem": 42
        },
        {
            "id": 64,
            "idOption": 15,
            "idItem": 43
        },
        {
            "id": 65,
            "idOption": 16,
            "idItem": 11
        },
        {
            "id": 66,
            "idOption": 16,
            "idItem": 12
        },
        {
            "id": 67,
            "idOption": 16,
            "idItem": 44
        },
        {
            "id": 68,
            "idOption": 16,
            "idItem": 45
        }
    ]
"""

const val supportTeamsList = """
    [
        {
            "id": 1,
            "description": "GUINCHOS"
        },
        {
            "id": 2,
            "description": "SOS - AMBULÂNCIA"
        },
        {
            "id": 3,
            "description": "SOS - RESGATE"
        },
        {
            "id": 4,
            "description": "BOMBEIROS"
        },
        {
            "id": 5,
            "description": "APOIO AMBIENTAL"
        },
        {
            "id": 6,
            "description": "PMRVEST"
        },
        {
            "id": 7,
            "description": "OUTROS"
        }
    ]
"""