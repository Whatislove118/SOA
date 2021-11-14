import React, {useEffect, useState} from 'react';
import {Content} from "../../components/ContentList/Content";
import {toast, Toaster} from "react-hot-toast";
import {PaginationList} from "../../paginationList/paginationList";
import {countPage} from "../../Utils/countPage";
import style from "../../paginationList/paginationList.scss"
import {ALL, FILTER, SORT} from "../../Utils/modes";

export const ContentPage = () => {
    const [mode, setMode] = useState("");
    const [content, setContent] = useState([]);
    const [id, setId] = useState("");
    const [name, setName] = useState("");
    const [coordinate_x, setCoordinateX] = useState("");
    const [coordinate_y, setCoordinateY] = useState("");
    const [creationData, setData] = useState("");
    const [area, setArea] = useState("");
    const [population, setPopulation] = useState("");
    const [metersAboveSeaLevel, setMASL] = useState("");
    const [establishmentDate, setEstablishmentDate] = useState("");
    const [government, setGovernment] = useState("");
    const [climate, setClimate] = useState("");
    const [governor_height, setGovernorHeight] = useState("");
    const [governor_birthday, setGovernorBirthday] = useState("");
    const [sortStructure, setSortStructure] = useState({
        id: false,
        name: false,
        coordinates_x: false,
        coordinates_y: false,
        area: false,
        population: false,
        metersAboveSeaLevel: false,
        establishmentDate: false,
        climate: false,
        government: false,
        governor_height: false,
        governor_birthday: false,
    });

    const [currentPage, setCurrentPage] = useState(1);
    const [perPage, setPerPage] = useState(5);
    const [totalCount, setTotalCount] = useState(10);
    const pagesCount = Math.ceil(totalCount/perPage)
    const pages = []
    countPage(pages, pagesCount, currentPage);

    useEffect(() => {
        switch (mode) {
            case ALL:
                getAll();
                break;
            case FILTER:
                sendFilter();
                break;
            case SORT:
                sendSort();
                break
        }
    }, [currentPage]);

    useEffect(() => {
        getAll();
    }, [])

    useEffect(() => {
        getOneCity();
    }, [id])

    useEffect(() => {
        // console.log(sortStructure);
        // let url = `http://localhost:8080/city?`
        // for (let field in sortStructure) {
        //     if (sortStructure[field] === true) {
        //         url += `sort=${field}&`;
        //     }
        // }
        // fetch(url)
        //     .then(res => res.json())
        //     .then(status)
        //     .then(
        //         (result) => {
        //             setContent(result);
        //             countPage(pages, pagesCount, currentPage);
        //             // getPage();
        //         }
        //     ).catch(error => {
        //     console.log('error, sry')
        // })
        setMode(SORT);
        sendSort();
    }, [sortStructure])

    useEffect(() => {
        setMode(FILTER);
        sendFilter();
    }, [id, name, coordinate_x, coordinate_y, creationData, area, population, metersAboveSeaLevel, establishmentDate, climate,
        government, governor_height, governor_birthday])

    const getOneCity = () => {
        fetch(`http://localhost:8080/city/${id}`)
            .then(res => res.json())
            .then(
                (result) => {
                    if (result.total > 0) {
                        setContent(result.content);
                        setTotalCount(result.total);
                    } else {
                        getAll();
                    }
                },
                (err) => {
                    setContent([]);
                    setTotalCount(0);
                }
            )
    }

    const getAll = () => {
        fetch(`http://localhost:8080/city?page=${currentPage}&pageSize=${perPage}`)
            .then(res => res.json())
            .then(
                (result) => {
                    setContent(result.content);
                    setTotalCount(result.total);
                },
                (error) => {
                    toast.error("Сервис сейчас недоступен");
                }
            )
    }

    const sendFilter = () => {
        fetch(`http://localhost:8080/Lab1/city/?page=${currentPage}&pageSize=${perPage}${createURL()}`)
            .then(res => res.json())
            .then(
                (result) => {
                    if (perPage > result.total) {
                        setCurrentPage(1);
                    }
                    setTotalCount(result.total);
                    setContent(result.content);
                },
                (error) => {
                    toast.error("Сервис сейчас недоступен");
                }
            )
    }

    const sendSort = () => {
        let url = `http://localhost:8080/Lab1/city?page=${currentPage}&pageSize=${perPage}&sort&`
        for (let field in sortStructure) {
            if (sortStructure[field] === true) {
                url += `${field}&`;
            }
        }
        fetch(url)
            .then(res => res.json())
            .then(
                (result) => {
                    setTotalCount(result.total);
                    setContent(result.content);
                },
                (error) => {
                    toast.error("Сервис сейчас недоступен");
                }
            )
    }

    const create = () => {
        fetch("http://localhost:8080/city/", {
            method: "POST",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(city())
        })
            .then(async (response) => {
                if (
                    Math.trunc(response.status / 100) === 4
                    || Math.trunc(response.status / 100) === 5
                    || response.status === 429
                ) {
                    toast.error(await response.text());
                } else {
                    toast.success("Созданно");
                    sendFilter();
                }
            })
            .catch((err) => {
                toast.error("Сервис сейчас недоступен");
            })
    }

    const deleteCity = (id) => {
        if (id !== "") {
            fetch("http://localhost:8080/city",{
                method: "DELETE",
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: JSON.stringify({
                    "id": parseInt(id)
                })
            })
            .then(async (response) => {
                if (
                    Math.trunc(response.status / 100) === 4
                    || Math.trunc(response.status / 100) === 5
                    || response.status === 429
                ) {
                    toast.error(await response.text());
                } else {
                    toast.success("Удалено");
                    sendFilter();
                }
            })
            .catch((err) => {
                toast.error("Сервис сейчас недоступен");
            })
        }
    }

    const deleteByClimate = () => {

        if (climate != "") {
            fetch(`http://localhost:8080/Lab1//delete/capital?climate=${climate}`, {
                method: "DELETE"
            })
                .then(async (response) => {
                    if (
                        Math.trunc(response.status / 100) === 4
                        || Math.trunc(response.status / 100) === 5
                        || response.status === 429
                    ) {
                        toast.error(await response.text());
                    } else {
                        toast.success("Удаленно");
                    }
                })
                .catch((err) => {
                    toast.error("Сервис сейчас недоступен");
                })
        } else {
            toast.error("Поле capital незаданно");
        }
    }

    const getWithHigherGovernment = () => {
        if (government != "") {
            fetch(`localhost:8080/city/by/government/higher?government=${government}`)
                .then(async (response) => {
                    if (
                        Math.trunc(response.status / 100) === 4
                        || Math.trunc(response.status / 100) === 5
                        || response.status === 429
                    ) {
                        toast.error(await response.text());
                    } else {
                        toast.success("Удаленно");
                    }
                })
                .catch((err) => {
                    toast.error("Сервис сейчас недоступен");
                })
        }
        // .then(async (response) => {
        //     if (
        //         Math.trunc(response.status / 100) === 4
        //         || Math.trunc(response.status / 100) === 5
        //         || response.status === 429
        //     ) {
        //         toast.error(await response.text());
        //     } else {
        //         setContent(await response.json());
        //     }
        // })  // .then(async (response) => {
        //     if (
        //         Math.trunc(response.status / 100) === 4
        //         || Math.trunc(response.status / 100) === 5
        //         || response.status === 429
        //     ) {
        //         toast.error(await response.text());
        //     } else {
        //         setContent(await response.json());
        //     }
        // })
    }

    const getWithLowerGovernment = () => {
        if (government != "") {
            fetch(`localhost:8080/city/by/government/lower?government=${government}`)
                .then(async (response) => {
                    if (
                        Math.trunc(response.status / 100) === 4
                        || Math.trunc(response.status / 100) === 5
                        || response.status === 429
                    ) {
                        toast.error(await response.text());
                    } else {
                        toast.success("Удаленно");
                    }
                })
                .catch((err) => {
                    toast.error("Сервис сейчас недоступен");
                })
        }
    }

    const sendUpdate = (id, field, content) => {
        fetch("http://localhost:8080/city/", {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(createJSON(id, field, content))
        })
            .then(async (response) => {
                if (
                    Math.trunc(response.status / 100) === 4
                    || Math.trunc(response.status / 100) === 5
                    || response.status === 429
                ) {
                    toast.error(await response.text());
                } else {
                    toast.success("Изменено");
                }
            })
    }

    const createURL = () => {
        let str ="?"
        if (id !== ""){
            str += `id=${id}`
        }
        if (name !== "") {
            str += `name=${name}&`
        } if (coordinate_x !== "") {
            str += `coordinates_x=${coordinate_x}&`;
        } if (coordinate_y !== "") {
            str += `coordinates_y=${coordinate_y}&`;
        } if (creationData !== "") {
            str += `creationDate=${creationData}&`;
        } if (area !== "") {
            str += `area=${area}&`;
        } if (population !== "") {
            str += `population=${population}&`;
        } if (metersAboveSeaLevel !== "") {
            str += `metersAboveSeaLevel=${metersAboveSeaLevel}&`;
        } if (establishmentDate !== "") {
            str += `establishmentDate=${establishmentDate}&`;
        } if (climate !== "") {
            str += `climate=${climate}&`;
        } if (government !== "") {
            str += `government=${government}&`;
        } if (governor_birthday !== "") {
            str += `governor_birthday=${governor_birthday}&`;
        }if (governor_height !== "") {
            str += `governor_height=${governor_height}&`;
        }
        return str;
    }

    //todo return
    const city = () => {
        let c = {
            name : name,
            coordinates : {
                x : parseInt(coordinate_x),
                y : parseFloat(coordinate_y)
            },
            area : parseInt(area),
            population : parseInt(population),
            metersAboveSeaLevel : parseInt(metersAboveSeaLevel),
            establishmentDate : establishmentDate,
            climate : climate,
            government : government,
            governor : {
                height : parseFloat(governor_height),
                birthday: governor_birthday
            }
        };

        return c;
    }
    const clearForm = () => {
        setId("");
        setName("");
        setCoordinateX("");
        setCoordinateY("");
        setData("");
        setArea("");
        setPopulation("");
        setMASL("");
        setEstablishmentDate("");
        setClimate("");
        setGovernment("");
        setGovernorBirthday("");
        setGovernorHeight("");
    }

    const checkEnter = (e, id, field, content) => {
        if (e.key === 'Enter' || e.keyCode === 13) {
            sendUpdate(id, field, content)
        }
    }

    return (
        <div>
            <button onClick={() => create()}>Создать</button>
            <button onClick={() => clearForm()}>Очистить</button>
            <button onClick={() => deleteCity()}>Удалить</button>
            <button onClick={() => deleteByClimate()}>delete by Climate</button>
            <button onClick={() => getWithHigherGovernment()}>Higher government</button>
            <button onClick={() => getWithLowerGovernment()}>Lower government</button>
            <Toaster />
        <table>
            <tr>
                <td>id<input name="id" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>name<input name="name" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>coordinates_x<input name="coordinates_x" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>coordinates_y<input name="coordinates_y" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>creationDate<input name="creationDate" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>area<input name="area" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>population<input name="population" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>metersAboveSeaLevel<input name="metersAboveSeaLevel" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>establishmentDate<input name="establishmentDate" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>climate<input name="climate" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>government<input name="government" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>governor_height<input name="governor_height" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td>governor_birthday<input name="governor_birthday" type="checkbox" onClick={(event) => setSortStructure({...sortStructure, [event.target.name]: event.target.checked})}/></td>
                <td> </td>
            </tr>

            <tr>
                <td><input type="text" value={id} onChange={(event) => setId(event.target.value)}/></td>
                <td><input type="text" value={name} onChange={(event) => setName(event.target.value)}/></td>
                <td><input type="text" value={coordinate_x} onChange={(event) => setCoordinateX(event.target.value)}/></td>
                <td><input type="text" value={coordinate_y} onChange={(event) => setCoordinateY(event.target.value)}/></td>
                <td><input type="date" value={creationData} onChange={(event) => setData(event.target.value)}/></td>
                <td><input type="text" value={area} onChange={(event) => setArea(event.target.value)}/></td>
                <td><input type="text" value={population} onChange={(event) => setPopulation(event.target.value)}/></td>
                <td><input type="text" value={metersAboveSeaLevel} onChange={(event) => setMASL(event.target.value)}/></td>
                <td><input type="text" value={establishmentDate} onChange={(event) => setEstablishmentDate(event.target.value)}/></td>
                <td><select value={climate} onChange={(event) => setClimate(event.target.value)}>
                    <option value=""> </option>
                    <option value="MONSOON">MONSOON</option>
                    <option value="HUMIDSUBTROPICAL">HUMIDSUBTROPICAL</option>
                    <option value="SUBARCTIC">SUBARCTIC</option>
                </select></td>
                <td><select value={government} onChange={(event) => setGovernment(event.target.value)}>
                    <option value=""> </option>
                    <option value="ANARCHY">ANARCHY</option>
                    <option value="DESPOTISM">DESPOTISM</option>
                    <option value="CORPORATOCRACY">CORPORATOCRACY</option>
                    <option value="MERITOCRACY">MERITOCRACY</option>
                    <option value="TOTALITARIANISM">TOTALITARIANISM</option>
                </select></td>
                <td><input type="text" value={governor_height} onChange={(event) => setGovernorHeight(event.target.value)}/></td>
                <td><input type="text" value={governor_birthday} onChange={(event) => setGovernorBirthday(event.target.value)}/></td>
                <td> </td>
            </tr>

            <Content
                sendUpdate={(id, name, content) => sendUpdate(id, name, content)}
                checkEnter={(e, id, name, content) => checkEnter(e, id, name, content)}
                onClick={(id) => deleteCity(id)}
                content={content}
            />
        </table>

            <div className="pages">
                {pages.map((page, index) => <span
                    key={index}
                    className={currentPage == page ? "current-page" : "page"}
                    onClick={() => {setCurrentPage(page)}}>{page}</span>)}
            </div>
        </div>
);
};