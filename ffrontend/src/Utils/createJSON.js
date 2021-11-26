export const createJSON = (id, field, content) => {

    switch (field) {
        case("name"):
            return {
                id: id,
                name: content
            }
        case ("coordinate_x"):
            console.log(parseInt(content))
            if(content == "" || isNaN(parseInt(content))){
                content = ""
                return {
                    id: id,
                    coordinate_x: content
                }
            }else {
                return {
                    id: id,
                    coordinate_x: parseInt(content)
                }
            }
        case ("coordinate_y"):
            if(content == "" || isNaN(parseFloat(content))) {
                content = ""
                return {
                    id: id,
                    coordinate_x: content
                }
            }else {
                return {
                    id: id,
                    coordinate_y: parseFloat(content)
                }
            }
        case ("area"):
            if(content == "" || isNaN(parseInt(content))) {
                content = ""
                return {
                    id: id,
                    area: content
                }
            }else{
                return {
                    id: id,
                    area: parseInt(content)
                }
            }
        case ("population"):
            if(content == "" || isNaN(parseInt(content))) {
                content = ""
                return {
                    id: id,
                    population: content
                }
            }else {
                return {
                    id: id,
                    population: parseInt(content)
                }
            }
        case ("metersAboveSeaLevel"):
            if(content == "" || isNaN(parseInt(content))) {
                content = ""
                return {
                    id: id,
                    metersAboveSeaLevel: content
                }
            }else{
                    return {
                        id: id,
                        metersAboveSeaLevel: parseInt(content)
                    }
            }
        case ("establishmentDate"):
                return {
                    id: id,
                    establishmentDate: content
                }

        // case (""):
        //     return {
        //         id: id,
        //         capital: Boolean(content)
        //     }
        case ("government"):
                return {
                    id: id,
                    government: content
                }
        case ("climate"):
            return {
                id: id,
                climate: content
            }
        case ("governor_height"):
            if(content == "" || isNaN(parseFloat(content))) {
                content = ""
                return {
                    id: id,
                    governor_height: content
                }
            }else{
                return {
                    id: id,
                    governor_height: parseFloat(content)
                }
            }

    }
}
