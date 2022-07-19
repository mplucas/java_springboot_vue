import axios from "axios"
import { parseISO } from "date-fns"

const isoDateFormat = /^\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}(?:\.\d*)?$/

const httpClient = axios.create({
    baseURL: 'http://localhost:3000/'
})

httpClient.interceptors.response.use(originalResponse => {
    convertStringDatesToDates(originalResponse.data)
    return originalResponse
})

function convertStringDatesToDates(body) {
    if (body === null || body === undefined || typeof body !== "object")
        return body
    for (const key of Object.keys(body)) {
        const value = body[key]
        if (isIsoDateString(value)) body[key] = parseISO(value)
        else if (typeof value === "object") convertStringDatesToDates(value)
    }
}

function isIsoDateString(value) {
    return value && typeof value === "string" && isoDateFormat.test(value)
}

httpClient.interceptors.request.use(originalRequest => {
    removeTimezoneFromDates(originalRequest.data)
    return originalRequest
})

function removeTimezoneFromDates(body) {
    if (body === null || body === undefined || typeof body !== "object")
        return body
    for (const key of Object.keys(body)) {
        const value = body[key]
        if (isDate(value)) body[key] = removeTimezoneFromDate(value)
        else if (typeof value === "object") convertStringDatesToDates(value)
    }
}

function isDate(value) {
    return value instanceof Date && !isNaN(value.valueOf())
}

function removeTimezoneFromDate(date) {
    return new Date(Date.UTC(date.getFullYear(), date.getMonth(),
        date.getDate(), date.getHours(),
        date.getMinutes(), date.getSeconds()));
}

export default httpClient