# Implementation Plan - Refinamento da Seleção do Bafômetro (Comportamento de Toggle)

Este plano visa ajustar a lógica de seleção na tela de Bafômetro para permitir que o usuário desmarque uma opção já selecionada, voltando o estado para nulo (comportamento de toggle).

## Proposed Changes

### [presenter] - ViewModel

#### [MODIFY] [CheckBreathalyzerViewModel.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/main/java/br/com/usinasantafe/cav/presenter/view/card/breathalyzer/check/CheckBreathalyzerViewModel.kt)
- Alterar `onChangeFlagRealized` para verificar se o valor clicado já é o atual. Se for igual, define como `null`.
- Garantir que ao desmarcar o bafômetro (`flagRealized` se tornando `null`), o resultado (`flagResult`) também seja limpo.
- Alterar `onChangeFlagResult` para implementar a mesma lógica de toggle (desmarcar se clicar na opção já selecionada).

## Verification Plan

### Manual Verification
- Abrir a tela de Bafômetro.
- Marcar "NÃO" e clicar novamente em "NÃO": a opção deve ser desmarcada e ficar nada selecionado.
- Marcar "SIM", marcar "POSITIVO", e depois clicar em "SIM" novamente: tanto o bafômetro quanto o resultado devem ser limpos.
- O mesmo comportamento deve ocorrer para "POSITIVO" e "NEGATIVO".
