function appendChooseMachineHeader() {
    if ($("#chooseMachineHeaderDiv").length === 0){
        $("#chooseMachine_Div").append(
            '<div class="row" id="chooseMachineHeaderDiv">' +
            '<div class="col-lg-2"></div>' +
            '<div class="col-lg-8">' +
            '<h5>Alege masina:</h5>' +
            '</div>' +
            '<div class="col-lg-2"></div>' +
            '</div>');
    }
}

function appendSelectEmployeeMachine(optionsSelected, employeesJSON){
    optionsSelected.each(function () {
        var employeeId = $(this).val();
        var employeeSelectMachine = $("#machineSelect-employee-" + employeeId);
        if (employeeSelectMachine.length == 0){
            $("#chooseMachine_Div").append(
                '<div class="row" id="chooseMachine-employee-' + employeeId + '">' +
                '   <div class="col-lg-2"></div>' +
                '   <div class="col-lg-2">' +
                '       <label class="control-label" for="machineSelect-employee-' + employeeId + '">' + $(this).text() + '</label>' +
                '   </div>' +
                '   <div class="col-lg-2">' +
                '       <select name="machineSelect_SOFER" id="machineSelect-employee-' + employeeId + '" class="selectpicker">' +
                '       </select>' +
                '   </div>' +
                '   <div class="col-lg-2"></div>' +
                '</div>');
            for (i in employeesJSON){
                if (employeesJSON[i].idEmployee == employeeId){
                    var employeeMachines = employeesJSON[i].machines;
                    for (j in employeeMachines){
                        var text = employeeMachines[j].licensePlate + " " + employeeMachines[j].capacity + " mc";
                        $("#machineSelect-employee-" + employeeId).append(new Option(text, employeeMachines[j].machineryId));
                    }
                    $("#machineSelect-employee-" + employeeId).selectpicker("refresh");
                }
            }
        }
    })
}

function removeUnselectedOption(optionsSelected, chooseMachineDivs){
    chooseMachineDivs.each(function () {
        var div_id = $(this).attr("id");
        var flag = false;
        if (optionsSelected.length > 0){
            optionsSelected.each(function () {
                if (div_id.search($(this).val()) > 0){
                    flag = true;
                }
            })
        } else {
            $(this).remove();
        }
        if ((flag === false) && ($(this).attr("id") !== "chooseMachineHeaderDiv")){
            $(this).remove();
        }
    })
}