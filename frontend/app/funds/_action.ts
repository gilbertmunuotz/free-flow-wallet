/* eslint-disable @typescript-eslint/no-explicit-any */
'use server'

import { auth } from "@/auth";
import { SERVER_URI } from "@/constants/constant"
import axios from "axios"

export async function PostFunds(formData: FormData): Promise<void> {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    const providerName = formData.get('providerName') as string
    const amount = formData.get('amount') as string

    try {
        const response = await axios.post(`${SERVER_URI}/api/v1/external-accounts/funds`, {
            providerName,
            amount
        }, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the balance data
    } catch (error: any) {
        console.error("Failed to Add Funds:", error);
        throw new Error("Failed to Add Funds");
    }
}

export async function GetFunds() {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    try {
        const response = await axios.get(`${SERVER_URI}/api/v1/external-accounts/history`, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the transaction data

    } catch (error: any) {
        console.error("Failed to Get Funds:", error);
        throw new Error("Failed to Get Funds");
    }
}