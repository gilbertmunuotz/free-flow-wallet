/* eslint-disable @typescript-eslint/no-explicit-any */
'use server'

import { auth } from "@/auth";
import { SERVER_URI } from "@/constants/constant"
import axios from "axios"

export async function P2P(formData: FormData): Promise<void> {
    const session = await auth(); // get the user session

    if (!session?.accessToken) {
        throw new Error("Not authenticated or token missing");
    }

    const receiverEmail = formData.get('receiverEmail') as string
    const amount = formData.get('amount') as string
    const pin = formData.get('pin') as string

    try {
        const response = await axios.post(`${SERVER_URI}/api/v1/transactions/p2p`, {
            receiverEmail,
            amount,
            pin
        }, {
            headers: {
                Authorization: `Bearer ${session.accessToken}`
            }
        });

        return response.data; // return the balance data
    } catch (error: any) {
        console.error("Failed to Perform Transaction:", error);
        throw new Error("Failed to Perform Transaction");
    }
}