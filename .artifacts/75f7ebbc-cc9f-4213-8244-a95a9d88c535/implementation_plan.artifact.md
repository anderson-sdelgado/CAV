# Plano de Correção: Digitação Decimal com Múltiplas Casas

Este plano visa corrigir as funções de manipulação de texto para suportar corretamente o número de casas decimais informado, resolvendo o problema onde a digitação em campos de 2 casas (como o bafômetro) resultava em valores multiplicados por 10.

## Mudanças Propostas

### [theme] - Ajuste na lógica do Teclado

#### [MODIFY] [Buttons.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/main/java/br/com/usinasantafe/cav/presenter/theme/Buttons.kt)
- Adicionar import `kotlin.math.pow`.
- Atualizar `addTextFieldComma` para calcular o divisor dinamicamente: `10.0.pow(decimalPlaces)`.
- Atualizar `clearTextFieldComma` para calcular o divisor dinamicamente: `10.0.pow(decimalPlaces)`.
- Isso garante que, se `decimalPlaces = 2`, o valor seja dividido por `100.0`, transformando "1" em "0,01" e "12" em "0,12".

### [presenter] - Ajuste na ViewModel

#### [MODIFY] [CountBreathalyzerViewModel.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/main/java/br/com/usinasantafe/cav/presenter/view/card/breathalyzer/count/CountBreathalyzerViewModel.kt)
- Atualizar a chamada de `clearTextFieldComma` para passar `COUNT_DECIMAL` (2), garantindo que ao apagar um número a formatação permaneça correta com 2 casas.

## Verificação

1. **Cenário 1 (Início)**: Texto é "0,00". Digita "1". Novo texto deve ser "0,01".
2. **Cenário 2 (Continuação)**: Texto é "0,01". Digita "2". Novo texto deve ser "0,12".
3. **Cenário 3 (Apagar)**: Texto é "0,12". Clica em "APAGAR". Novo texto deve ser "0,01".
