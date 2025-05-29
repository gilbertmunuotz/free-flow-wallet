/* eslint-disable @typescript-eslint/no-explicit-any */
'use server'

import { auth } from "@/auth";
import { SERVER_URI } from "@/constants/constant"
import axios from "axios"

export async function GetUser() {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    try {
        const response = await axios.get(`${SERVER_URI}/api/v1/users/user`, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the transaction data

    } catch (error: any) {
        console.error("Failed to fetch User:", error.message);
        throw new Error("Could not fetch user");
    }
}

export async function UpdateUser(formData: FormData): Promise<void> {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    const fullName = formData.get('fullName') as string
    const email = formData.get('email') as string
    const password = formData.get('password') as string
    const pin = formData.get('pin') as string

    try {
        const response = await axios.post(`${SERVER_URI}/api/v1/users/update`, {
            fullName,
            email,
            password,
            pin
        }, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the balance data
    } catch (error: any) {
        console.error("Failed to Update User:", error);
        throw new Error("Failed to Update User");
    }
}