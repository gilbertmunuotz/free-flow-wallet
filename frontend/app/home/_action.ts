/* eslint-disable @typescript-eslint/no-explicit-any */
'use server'

import { auth } from "@/auth";
import { SERVER_URI } from "@/constants/constant"
import axios from "axios"

export default async function getBalance() {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    try {
        const response = await axios.post(`${SERVER_URI}/api/v1/wallet/balance`, {}, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the balance data

    } catch (error: any) {
        console.error("Failed to fetch balance:", error.message);
        throw new Error("Could not fetch balance");
    }
}
